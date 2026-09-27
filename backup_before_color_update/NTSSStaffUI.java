import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class NTSSStaffUI extends JFrame {

    private final FloorPlanController controller;
    private final FloorPlanCanvas canvas;

    private final JComboBox<String> shapeBox;
    private final JComboBox<String> sizeBox;

    private final JLabel statusLabel;
    private JLabel boothCountLabel;
    private JLabel areaLabel;
    private JLabel priceLabel;
    private JLabel dimensionsLabel;

    private String selectedShape = "circle";
    private String selectedSize = "small";

    public NTSSStaffUI(FloorPlanController controller) {

        this.controller = controller;

        setTitle("NTSS Booth Floor Plan System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel header = new JPanel(
                new BorderLayout(10, 10));

        JLabel title = new JLabel(
                "NTSS EXHIBITION BOOTH FLOOR PLAN",
                SwingConstants.CENTER);

        title.setFont(
                new Font("Arial", Font.BOLD, 22));

        header.add(title, BorderLayout.NORTH);

        JPanel controls = new JPanel(
                new FlowLayout(FlowLayout.CENTER, 10, 5));

        controls.add(new JLabel("Shape:"));

        shapeBox = new JComboBox<>(
                new String[]{
                        "circle",
                        "square",
                        "rectangle"
                });

        controls.add(shapeBox);

        controls.add(new JLabel("Size:"));

        sizeBox = new JComboBox<>(
                new String[]{
                        "small",
                        "medium",
                        "large"
                });

        controls.add(sizeBox);

        JButton placeButton =
                new JButton("Place Booth");

        controls.add(placeButton);

        JButton saveButton =
                new JButton("Save Floor Plan");

        controls.add(saveButton);

        JButton refreshButton =
                new JButton("Refresh Stats");

        controls.add(refreshButton);

        header.add(controls, BorderLayout.CENTER);

        JPanel palette =
                createPalettePanel();

        header.add(palette, BorderLayout.SOUTH);

        add(header, BorderLayout.NORTH);

        canvas = new FloorPlanCanvas(
                controller,
                this);

        canvas.setPreferredSize(
                new Dimension(
                        controller.getFloorPlan().getWidth(),
                        controller.getFloorPlan().getHeight()));

        add(canvas, BorderLayout.CENTER);

        JPanel statistics =
                createStatisticsPanel();

        add(statistics, BorderLayout.EAST);

        statusLabel = new JLabel(
                "Ready. Select a booth type and click Place Booth.",
                SwingConstants.LEFT);

        statusLabel.setBorder(
                BorderFactory.createEmptyBorder(
                        8, 12, 8, 12));

        add(statusLabel, BorderLayout.SOUTH);

        shapeBox.addActionListener(e -> {

            selectedShape =
                    (String) shapeBox.getSelectedItem();

            updateSelectionText();
        });

        sizeBox.addActionListener(e -> {

            selectedSize =
                    (String) sizeBox.getSelectedItem();

            updateSelectionText();
        });

        placeButton.addActionListener(e -> {

            selectedShape =
                    (String) shapeBox.getSelectedItem();

            selectedSize =
                    (String) sizeBox.getSelectedItem();

            canvas.setPlacementMode(true);

            statusLabel.setText(
                    "Placement mode: "
                            + capitalize(selectedShape)
                            + " "
                            + capitalize(selectedSize)
                            + ". Click anywhere on the canvas.");
        });

        saveButton.addActionListener(e ->
                saveFloorPlan());

        refreshButton.addActionListener(e ->
                updateStatistics());

        updateStatistics();

        pack();

        setLocationRelativeTo(null);
    }

    private JPanel createStatisticsPanel() {

        JPanel panel =
                new JPanel(
                        new GridLayout(8, 1, 5, 8));

        panel.setBorder(
                BorderFactory.createTitledBorder(
                        "Floor Plan Information"));

        boothCountLabel =
                new JLabel();

        areaLabel =
                new JLabel();

        priceLabel =
                new JLabel();

        dimensionsLabel =
                new JLabel();

        panel.add(
                createSectionLabel(
                        "SYSTEM STATUS"));

        panel.add(boothCountLabel);

        panel.add(areaLabel);

        panel.add(priceLabel);

        panel.add(dimensionsLabel);

        panel.add(
                new JLabel(
                        "Patterns Used:"));

        panel.add(
                new JLabel(
                        "Controller / Creator / Expert"));

        panel.add(
                new JLabel(
                        "Composite / Iterator / Flyweight"));

        return panel;
    }

    private JLabel createSectionLabel(
            String text) {

        JLabel label =
                new JLabel(text);

        label.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14));

        return label;
    }

    private JPanel createPalettePanel() {

        JPanel panel =
                new JPanel(
                        new GridLayout(
                                2,
                                5,
                                5,
                                5));

        String[] shapes = {
                "circle",
                "square",
                "rectangle"
        };

        String[] sizes = {
                "small",
                "medium",
                "large"
        };

        for (String shape : shapes) {

            for (String size : sizes) {

                JButton button =
                        new JButton(
                                capitalize(shape)
                                        + " "
                                        + capitalize(size));

                button.addActionListener(e -> {

                    selectedShape =
                            shape;

                    selectedSize =
                            size;

                    shapeBox.setSelectedItem(
                            shape);

                    sizeBox.setSelectedItem(
                            size);

                    canvas.setPlacementMode(
                            true);

                    statusLabel.setText(
                            "Selected: "
                                    + capitalize(shape)
                                    + " "
                                    + capitalize(size)
                                    + ". Click the canvas.");
                });

                panel.add(button);
            }
        }

        JButton infoButton =
                new JButton("System Info");

        infoButton.addActionListener(e ->
                showSystemInfo());

        panel.add(infoButton);

        return panel;
    }

    private void updateSelectionText() {

        statusLabel.setText(
                "Selected booth type: "
                        + capitalize(selectedShape)
                        + " "
                        + capitalize(selectedSize)
                        + ".");
    }

    private void updateStatistics() {

        int count =
                controller.getFloorPlan()
                        .getBoothCount();

        float totalPrice = 0;

        int totalArea = 0;

        Iterator<Booth> iterator =
                controller.getFloorPlan().iterator();

        while (iterator.hasNext()) {

            Booth booth =
                    iterator.next();

            totalPrice += booth.getPrice();

            totalArea +=
                    booth.getWidth()
                            * booth.getHeight();
        }

        boothCountLabel.setText(
                "Booths: " + count);

        areaLabel.setText(
                "Occupied Area: "
                        + totalArea);

        priceLabel.setText(
                "Listed Value: $"
                        + String.format(
                                "%.2f",
                                totalPrice));

        dimensionsLabel.setText(
                "Canvas: "
                        + controller.getFloorPlan()
                                .getWidth()
                        + " x "
                        + controller.getFloorPlan()
                                .getHeight());

        if (canvas != null) {
            canvas.repaint();
        }
    }

    private void saveFloorPlan() {

        if (controller.getFloorPlan()
                .getBoothCount() == 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Place at least one booth before saving.",
                    "Nothing to Save",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        JFileChooser chooser =
                new JFileChooser();

        chooser.setSelectedFile(
                new File("ntss_floor_plan.txt"));

        int result =
                chooser.showSaveDialog(this);

        if (result != JFileChooser.APPROVE_OPTION) {
            return;
        }

        boolean saved =
                controller.saveFloorPlan(
                        chooser.getSelectedFile());

        if (saved) {

            statusLabel.setText(
                    "Floor plan saved successfully.");

            JOptionPane.showMessageDialog(
                    this,
                    "Floor plan saved successfully.",
                    "Save Complete",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void showSystemInfo() {

        String message =
                "NTSS Booth Floor Plan System\n\n"
                        + "The application demonstrates:\n\n"
                        + "Controller Pattern\n"
                        + "Creator Pattern\n"
                        + "Expert Pattern\n"
                        + "Composite Pattern\n"
                        + "Iterator Pattern\n"
                        + "Flyweight Pattern\n\n"
                        + "The floor plan supports booth placement,\n"
                        + "validation, drawing, statistics, and saving.";

        JOptionPane.showMessageDialog(
                this,
                message,
                "System Information",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public String getSelectedShape() {
        return selectedShape;
    }

    public String getSelectedSize() {
        return selectedSize;
    }

    public void showPlacementError(
            String message) {

        JOptionPane.showMessageDialog(
                this,
                message,
                "Placement Error",
                JOptionPane.WARNING_MESSAGE);

        statusLabel.setText(
                "Placement failed: " + message);
    }

    public void reportBoothPlaced(
            Booth booth) {

        statusLabel.setText(
                "Booth placed: "
                        + capitalize(
                                booth.getShape())
                        + " "
                        + capitalize(
                                booth.getSize())
                        + " at ("
                        + booth.getxLocation()
                        + ", "
                        + booth.getyLocation()
                        + ").");

        updateStatistics();
    }

    private String capitalize(
            String text) {

        if (text == null
                || text.isEmpty()) {

            return text;
        }

        return text.substring(0, 1)
                        .toUpperCase()
                + text.substring(1);
    }
}

