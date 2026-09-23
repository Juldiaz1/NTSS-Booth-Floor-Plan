
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.File;

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

    private String selectedShape = "circle";
    private String selectedSize = "small";

    public NTSSStaffUI(FloorPlanController controller) {
        this.controller = controller;

        setTitle("NTSS Booth Floor Plan System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel(new BorderLayout());

        JPanel selectionPanel = new JPanel(
                new FlowLayout(FlowLayout.LEFT));

        selectionPanel.add(new JLabel("Shape:"));

        shapeBox = new JComboBox<>(
                new String[]{"circle", "square", "rectangle"});

        selectionPanel.add(shapeBox);

        selectionPanel.add(new JLabel("Size:"));

        sizeBox = new JComboBox<>(
                new String[]{"small", "medium", "large"});

        selectionPanel.add(sizeBox);

        JButton selectButton = new JButton("Select Booth");
        selectionPanel.add(selectButton);

        JButton saveButton = new JButton("Save");
        selectionPanel.add(saveButton);

        topPanel.add(selectionPanel, BorderLayout.NORTH);

        JPanel palettePanel = createPalettePanel();
        topPanel.add(palettePanel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);

        canvas = new FloorPlanCanvas(controller, this);
        canvas.setPreferredSize(
                new Dimension(
                        controller.getFloorPlan().getWidth(),
                        controller.getFloorPlan().getHeight()));

        add(canvas, BorderLayout.CENTER);

        statusLabel = new JLabel(
                "Select a booth and click a location on the canvas.",
                SwingConstants.LEFT);

        statusLabel.setBorder(
                BorderFactory.createEmptyBorder(5, 10, 5, 10));

        add(statusLabel, BorderLayout.SOUTH);

        shapeBox.addActionListener(e -> {
            selectedShape = (String) shapeBox.getSelectedItem();
        });

        sizeBox.addActionListener(e -> {
            selectedSize = (String) sizeBox.getSelectedItem();
        });

        selectButton.addActionListener(e -> {
            selectedShape = (String) shapeBox.getSelectedItem();
            selectedSize = (String) sizeBox.getSelectedItem();

            statusLabel.setText(
                    "Selected: "
                            + selectedShape
                            + " "
                            + selectedSize
                            + ". Click the canvas.");

            canvas.setPlacementMode(true);
        });

        saveButton.addActionListener(e -> saveFloorPlan());

        pack();
        setLocationRelativeTo(null);
    }

    private JPanel createPalettePanel() {
        JPanel panel = new JPanel(new GridLayout(3, 3, 5, 5));

        String[] shapes = {
            "circle", "square", "rectangle"
        };

        String[] sizes = {
            "small", "medium", "large"
        };

        for (String shape : shapes) {
            for (String size : sizes) {
                JButton button = new JButton(
                        capitalize(shape) + " " + capitalize(size));

                button.addActionListener(e -> {
                    selectedShape = shape;
                    selectedSize = size;

                    shapeBox.setSelectedItem(shape);
                    sizeBox.setSelectedItem(size);

                    statusLabel.setText(
                            "Selected: " + shape + " " + size
                                    + ". Click the canvas to place it.");

                    canvas.setPlacementMode(true);
                });

                panel.add(button);
            }
        }

        return panel;
    }

    private void saveFloorPlan() {
        if (controller.getFloorPlan().getBoothCount() == 0) {
            JOptionPane.showMessageDialog(
                    this,
                    "Place at least one booth before saving.",
                    "Nothing to Save",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(new File("floor_plan.txt"));

        int result = chooser.showSaveDialog(this);

        if (result != JFileChooser.APPROVE_OPTION) {
            return;
        }

        boolean saved = controller.saveFloorPlan(
                chooser.getSelectedFile());

        if (saved) {
            JOptionPane.showMessageDialog(
                    this,
                    "Floor plan is saved successfully.",
                    "Save Successful",
                    JOptionPane.INFORMATION_MESSAGE);

            statusLabel.setText(
                    "Floor plan is saved successfully.");
        }
    }

    public String getSelectedShape() {
        return selectedShape;
    }

    public String getSelectedSize() {
        return selectedSize;
    }

    public void showPlacementError(String message) {
        JOptionPane.showMessageDialog(
                this,
                message,
                "Placement Error",
                JOptionPane.WARNING_MESSAGE);
    }

    private String capitalize(String text) {
        return text.substring(0, 1).toUpperCase()
                + text.substring(1);
    }
}
