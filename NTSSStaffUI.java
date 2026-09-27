import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JColorChooser;
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
    private final JComboBox<String> backgroundBox;

    private final JLabel statusLabel;
    private final JLabel selectedColorLabel;
    private final JLabel backgroundColorLabel;

    private String selectedShape =
            "circle";

    private String selectedSize =
            "small";

    private Color selectedBoothColor =
            new Color(
                    75,
                    130,
                    220);

    public NTSSStaffUI(
            FloorPlanController controller) {

        this.controller =
                controller;

        setTitle(
                "NTSS Booth Floor Plan System");

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE);

        setLayout(
                new BorderLayout(
                        8,
                        8));

        JPanel header =
                new JPanel(
                        new BorderLayout(
                                8,
                                8));

        JLabel title =
                new JLabel(
                        "NTSS EXHIBITION BOOTH FLOOR PLAN",
                        SwingConstants.CENTER);

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        22));

        title.setBorder(
                BorderFactory.createEmptyBorder(
                        8,
                        8,
                        2,
                        8));

        header.add(
                title,
                BorderLayout.NORTH);

        JPanel controls =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                8,
                                5));

        controls.add(
                new JLabel("Shape:"));

        shapeBox =
                new JComboBox<>(
                        new String[]{
                                "circle",
                                "square",
                                "rectangle"
                        });

        controls.add(
                shapeBox);

        controls.add(
                new JLabel("Size:"));

        sizeBox =
                new JComboBox<>(
                        new String[]{
                                "small",
                                "medium",
                                "large"
                        });

        controls.add(
                sizeBox);

        JButton placeButton =
                new JButton(
                        "Place Booth");

        controls.add(
                placeButton);

        JButton boothColorButton =
                new JButton(
                        "Booth Color");

        controls.add(
                boothColorButton);

        JButton backgroundButton =
                new JButton(
                        "Custom Background");

        controls.add(
                backgroundButton);

        backgroundBox =
                new JComboBox<>(
                        new String[]{
                                "Light",
                                "Blueprint",
                                "Night",
                                "Warm",
                                "Custom"
                        });

        controls.add(
                new JLabel(
                        "Background:"));

        controls.add(
                backgroundBox);

        JButton saveButton =
                new JButton(
                        "Save Floor Plan");

        controls.add(
                saveButton);

        header.add(
                controls,
                BorderLayout.CENTER);

        JPanel palette =
                createPalettePanel();

        header.add(
                palette,
                BorderLayout.SOUTH);

        add(
                header,
                BorderLayout.NORTH);

        canvas =
                new FloorPlanCanvas(
                        controller,
                        this);

        canvas.setPreferredSize(
                new Dimension(
                        controller
                                .getFloorPlan()
                                .getWidth(),
                        controller
                                .getFloorPlan()
                                .getHeight()));

        add(
                canvas,
                BorderLayout.CENTER);

        JPanel bottom =
                new JPanel(
                        new BorderLayout());

        selectedColorLabel =
                new JLabel(
                        "Booth Color");

        selectedColorLabel.setBorder(
                BorderFactory.createEmptyBorder(
                        5,
                        10,
                        5,
                        10));

        backgroundColorLabel =
                new JLabel(
                        "Background");

        backgroundColorLabel.setBorder(
                BorderFactory.createEmptyBorder(
                        5,
                        10,
                        5,
                        10));

        JPanel colorInfo =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                5,
                                2));

        colorInfo.add(
                selectedColorLabel);

        colorInfo.add(
                backgroundColorLabel);

        bottom.add(
                colorInfo,
                BorderLayout.WEST);

        statusLabel =
                new JLabel(
                        "Ready. Select a booth style or click "
                                + "Place Booth.",
                        SwingConstants.RIGHT);

        statusLabel.setBorder(
                BorderFactory.createEmptyBorder(
                        5,
                        10,
                        5,
                        10));

        bottom.add(
                statusLabel,
                BorderLayout.CENTER);

        add(
                bottom,
                BorderLayout.SOUTH);

        shapeBox.addActionListener(e -> {

            selectedShape =
                    (String)
                            shapeBox
                                    .getSelectedItem();

            updateSelectionText();
        });

        sizeBox.addActionListener(e -> {

            selectedSize =
                    (String)
                            sizeBox
                                    .getSelectedItem();

            updateSelectionText();
        });

        placeButton.addActionListener(e -> {

            selectedShape =
                    (String)
                            shapeBox
                                    .getSelectedItem();

            selectedSize =
                    (String)
                            sizeBox
                                    .getSelectedItem();

            canvas.setPlacementMode(
                    true);

            statusLabel.setText(
                    "Placement mode: "
                            + capitalize(
                                    selectedShape)
                            + " "
                            + capitalize(
                                    selectedSize)
                            + ". Click the canvas.");
        });

        boothColorButton.addActionListener(
                e -> chooseBoothColor());

        backgroundButton.addActionListener(
                e -> chooseCustomBackground());

        backgroundBox.addActionListener(
                e -> applyBackgroundSelection());

        saveButton.addActionListener(
                e -> saveFloorPlan());

        updateColorLabels();

        pack();

        setLocationRelativeTo(null);
    }

    private JPanel createPalettePanel() {

        JPanel panel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                5,
                                3));

        String[] colors = {
                "Blue",
                "Green",
                "Orange",
                "Red",
                "Purple",
                "Teal",
                "Gold",
                "Pink"
        };

        Color[] values = {
                new Color(
                        75,
                        130,
                        220),

                new Color(
                        70,
                        170,
                        105),

                new Color(
                        235,
                        145,
                        55),

                new Color(
                        215,
                        75,
                        75),

                new Color(
                        135,
                        90,
                        190),

                new Color(
                        55,
                        165,
                        165),

                new Color(
                        215,
                        175,
                        45),

                new Color(
                        220,
                        105,
                        165)
        };

        for (int i = 0;
                i < colors.length;
                i++) {

            final Color color =
                    values[i];

            JButton button =
                    new JButton(
                            colors[i]);

            button.setFocusPainted(
                    false);

            button.addActionListener(
                    e -> {

                        selectedBoothColor =
                                color;

                        Booth selected =
                                canvas
                                        .getSelectedBooth();

                        if (selected != null) {

                            canvas
                                    .setSelectedBoothColor(
                                            color);

                            statusLabel.setText(
                                    "Changed the selected booth color.");

                        } else {

                            statusLabel.setText(
                                    "New booths will use "
                                            + colors[
                                                    java.util.Arrays
                                                            .asList(
                                                                    values)
                                                            .indexOf(
                                                                    color)]
                                            + ".");
                        }

                        updateColorLabels();
                        canvas.repaint();
                    });

            panel.add(button);
        }

        JButton infoButton =
                new JButton(
                        "How to Use");

        infoButton.addActionListener(
                e -> showHowToUse());

        panel.add(infoButton);

        return panel;
    }

    private void chooseBoothColor() {

        Color chosen =
                JColorChooser.showDialog(
                        this,
                        "Choose Booth Color",
                        selectedBoothColor);

        if (chosen == null) {
            return;
        }

        selectedBoothColor =
                chosen;

        Booth selected =
                canvas.getSelectedBooth();

        if (selected != null) {

            canvas.setSelectedBoothColor(
                    chosen);

            statusLabel.setText(
                    "Selected booth color changed.");

        } else {

            statusLabel.setText(
                    "New booths will use the selected color.");
        }

        updateColorLabels();
        canvas.repaint();
    }

    private void chooseCustomBackground() {

        Color chosen =
                JColorChooser.showDialog(
                        this,
                        "Choose Floor Plan Background",
                        canvas.getCanvasBackground());

        if (chosen == null) {
            return;
        }

        canvas.setCanvasBackground(
                chosen);

        backgroundBox.setSelectedItem(
                "Custom");

        statusLabel.setText(
                "Floor plan background changed.");

        updateColorLabels();
    }

    private void applyBackgroundSelection() {

        String value =
                (String)
                        backgroundBox
                                .getSelectedItem();

        if (value == null) {
            return;
        }

        Color color;

        switch (value) {

            case "Blueprint":
                color =
                        new Color(
                                28,
                                67,
                                100);
                break;

            case "Night":
                color =
                        new Color(
                                38,
                                42,
                                48);
                break;

            case "Warm":
                color =
                        new Color(
                                247,
                                239,
                                225);
                break;

            case "Custom":
                return;

            default:
                color =
                        new Color(
                                245,
                                247,
                                250);
                break;
        }

        canvas.setCanvasBackground(
                color);

        statusLabel.setText(
                "Background changed to "
                        + value
                        + ".");

        updateColorLabels();
    }

    private void updateColorLabels() {

        selectedColorLabel.setText(
                "Booth Color: RGB "
                        + selectedBoothColor.getRed()
                        + ", "
                        + selectedBoothColor.getGreen()
                        + ", "
                        + selectedBoothColor.getBlue());

        Color bg =
                canvas.getCanvasBackground();

        backgroundColorLabel.setText(
                "Background: RGB "
                        + bg.getRed()
                        + ", "
                        + bg.getGreen()
                        + ", "
                        + bg.getBlue());
    }

    private void updateSelectionText() {

        statusLabel.setText(
                "Selected: "
                        + capitalize(
                                selectedShape)
                        + " "
                        + capitalize(
                                selectedSize)
                        + ".");
    }

    public void showBoothSelected(
            Booth booth) {

        statusLabel.setText(
                "Selected Booth: "
                        + booth.getShape()
                        + " "
                        + booth.getSize()
                        + " at ("
                        + booth.getX()
                        + ", "
                        + booth.getY()
                        + "). Use Booth Color to recolor it.");
    }

    public void showCanvasSelected() {

        statusLabel.setText(
                "No booth selected. Choose a booth type or place a new booth.");
    }

    public Color getSelectedBoothColor() {

        return selectedBoothColor;
    }

    public String getSelectedShape() {
        return selectedShape;
    }

    public String getSelectedSize() {
        return selectedSize;
    }

    public void reportBoothPlaced(
            Booth booth) {

        statusLabel.setText(
                "Placed "
                        + capitalize(
                                booth.getShape())
                        + " "
                        + capitalize(
                                booth.getSize())
                        + " at ("
                        + booth.getX()
                        + ", "
                        + booth.getY()
                        + "). Click a booth to select it.");
    }

    public void showPlacementError(
            String message) {

        JOptionPane.showMessageDialog(
                this,
                message,
                "Placement Error",
                JOptionPane.WARNING_MESSAGE);

        statusLabel.setText(
                "Placement failed: "
                        + message);
    }

    private void saveFloorPlan() {

        if (controller
                .getFloorPlan()
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
                new File(
                        "ntss_floor_plan.txt"));

        int result =
                chooser.showSaveDialog(
                        this);

        if (result
                != JFileChooser.APPROVE_OPTION) {

            return;
        }

        boolean saved =
                controller.saveFloorPlan(
                        chooser.getSelectedFile());

        if (saved) {

            JOptionPane.showMessageDialog(
                    this,
                    "Floor plan saved successfully.",
                    "Save Complete",
                    JOptionPane.INFORMATION_MESSAGE);

            statusLabel.setText(
                    "Floor plan saved successfully.");
        }
    }

    private void showHowToUse() {

        String message =
                "NTSS Booth Floor Plan Controls\n\n"
                        + "1. Select Shape and Size.\n"
                        + "2. Select a color.\n"
                        + "3. Click Place Booth.\n"
                        + "4. Click the floor plan to place it.\n\n"
                        + "To recolor a booth:\n"
                        + "1. Click an existing booth.\n"
                        + "2. Click a color button or Booth Color.\n\n"
                        + "Background:\n"
                        + "Use the Background menu for presets.\n"
                        + "Use Custom Background for any color.\n\n"
                        + "The application still uses:\n"
                        + "Controller, Creator, Expert,\n"
                        + "Composite, Iterator, and Flyweight.";

        JOptionPane.showMessageDialog(
                this,
                message,
                "How to Use",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private String capitalize(
            String text) {

        if (text == null
                || text.isEmpty()) {

            return text;
        }

        return text.substring(
                        0,
                        1)
                .toUpperCase()
                + text.substring(1);
    }
}
