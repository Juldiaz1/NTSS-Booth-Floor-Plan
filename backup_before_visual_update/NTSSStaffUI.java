import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
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
    private final JComboBox<String> modeBox;
    private final JLabel statusLabel;
    private final JLabel colorLabel;
    private final JPanel colorPreview;

    private String selectedShape = "circle";
    private String selectedSize = "small";
    private Color selectedColor = new Color(70, 130, 180);
    private boolean threeD = true;

    public NTSSStaffUI(FloorPlanController controller) {
        this.controller = controller;

        setTitle("NTSS Booth Floor Plan System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel(new BorderLayout(8, 8));
        topPanel.setBorder(
                BorderFactory.createEmptyBorder(10, 10, 5, 10));

        JPanel controls = new JPanel(
                new FlowLayout(FlowLayout.LEFT, 10, 5));

        controls.add(new JLabel("Shape:"));

        shapeBox = new JComboBox<>(
                new String[]{"circle", "square", "rectangle"});
        controls.add(shapeBox);

        controls.add(new JLabel("Size:"));

        sizeBox = new JComboBox<>(
                new String[]{"small", "medium", "large"});
        controls.add(sizeBox);

        controls.add(new JLabel("View:"));

        modeBox = new JComboBox<>(
                new String[]{"2D", "3D"});
        modeBox.setSelectedItem("3D");
        controls.add(modeBox);

        JButton colorButton = new JButton("Choose Color");
        controls.add(colorButton);

        colorPreview = new JPanel();
        colorPreview.setPreferredSize(new Dimension(35, 25));
        colorPreview.setBackground(selectedColor);
        colorPreview.setBorder(
                BorderFactory.createLineBorder(Color.BLACK));

        controls.add(colorPreview);

        colorLabel = new JLabel(getColorText());
        controls.add(colorLabel);

        JButton selectButton = new JButton("Place Booth");
        controls.add(selectButton);

        JButton saveButton = new JButton("Save");
        controls.add(saveButton);

        topPanel.add(controls, BorderLayout.NORTH);

        JPanel paletteTitle = new JPanel(
                new FlowLayout(FlowLayout.LEFT));

        paletteTitle.add(new JLabel(
                "Booth Palette - choose a shape and size:"));

        topPanel.add(paletteTitle, BorderLayout.CENTER);

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
                "Choose a booth, color, and view mode, then click Place Booth.",
                SwingConstants.LEFT);

        statusLabel.setBorder(
                BorderFactory.createEmptyBorder(5, 10, 10, 10));

        add(statusLabel, BorderLayout.SOUTH);

        shapeBox.addActionListener(e -> {
            selectedShape = (String) shapeBox.getSelectedItem();
        });

        sizeBox.addActionListener(e -> {
            selectedSize = (String) sizeBox.getSelectedItem();
        });

        modeBox.addActionListener(e -> {
            threeD = "3D".equals(modeBox.getSelectedItem());

            statusLabel.setText(
                    "View mode: "
                            + (threeD ? "3D" : "2D")
                            + ".");
        });

        colorButton.addActionListener(e -> chooseColor());

        selectButton.addActionListener(e -> {
            selectedShape = (String) shapeBox.getSelectedItem();
            selectedSize = (String) sizeBox.getSelectedItem();
            threeD = "3D".equals(modeBox.getSelectedItem());

            statusLabel.setText(
                    "Selected: "
                            + selectedShape
                            + " "
                            + selectedSize
                            + " "
                            + (threeD ? "3D" : "2D")
                            + ". Click the canvas.");

            canvas.setPlacementMode(true);
        });

        saveButton.addActionListener(e -> saveFloorPlan());

        pack();
        setLocationRelativeTo(null);
    }

    private JPanel createPalettePanel() {
        JPanel panel = new JPanel(
                new GridLayout(3, 3, 8, 8));

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
                JButton button = new JButton(
                        capitalize(shape)
                                + " "
                                + capitalize(size));

                button.setPreferredSize(
                        new Dimension(130, 38));

                button.addActionListener(e -> {
                    selectedShape = shape;
                    selectedSize = size;

                    shapeBox.setSelectedItem(shape);
                    sizeBox.setSelectedItem(size);

                    statusLabel.setText(
                            "Selected: "
                                    + shape
                                    + " "
                                    + size
                                    + ". Click Place Booth.");

                    canvas.setPlacementMode(true);
                });

                panel.add(button);
            }
        }

        return panel;
    }

    private void chooseColor() {
        Color newColor = JColorChooser.showDialog(
                this,
                "Choose Booth Color",
                selectedColor);

        if (newColor == null) {
            return;
        }

        selectedColor = newColor;

        colorPreview.setBackground(selectedColor);
        colorLabel.setText(getColorText());

        statusLabel.setText(
                "Color selected: " + getColorText());
    }

    private String getColorText() {
        return "RGB "
                + selectedColor.getRed()
                + ","
                + selectedColor.getGreen()
                + ","
                + selectedColor.getBlue();
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
        chooser.setSelectedFile(
                new File("floor_plan.txt"));

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

    public Color getSelectedColor() {
        return selectedColor;
    }

    public boolean isThreeD() {
        return threeD;
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
