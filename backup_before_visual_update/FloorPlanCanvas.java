import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class FloorPlanCanvas extends JPanel {
    private final FloorPlanController controller;
    private final NTSSStaffUI ui;

    private boolean placementMode = false;

    public FloorPlanCanvas(
            FloorPlanController controller,
            NTSSStaffUI ui) {

        this.controller = controller;
        this.ui = ui;

        setBackground(Color.WHITE);

        setBorder(BorderFactory.createLineBorder(
                Color.BLACK, 2));

        addMouseListener(
                new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(
                            java.awt.event.MouseEvent event) {

                        placeBooth(
                                event.getX(),
                                event.getY());
                    }
                });
    }

    private void placeBooth(int x, int y) {
        if (!placementMode) {
            return;
        }

        String shape = ui.getSelectedShape();
        String size = ui.getSelectedSize();
        Color color = ui.getSelectedColor();
        boolean threeD = ui.isThreeD();

        try {
            controller.placeBooth(
                    x,
                    y,
                    shape,
                    size,
                    color,
                    threeD);

            placementMode = false;

            repaint();

        } catch (IllegalArgumentException exception) {
            ui.showPlacementError(
                    exception.getMessage());
        }
    }

    public void setPlacementMode(
            boolean placementMode) {

        this.placementMode = placementMode;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D g =
                (Graphics2D) graphics.create();

        g.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        drawGrid(g);

        controller.getFloorPlan().draw(g);

        g.dispose();
    }

    private void drawGrid(Graphics2D g) {
        g.setColor(new Color(225, 225, 225));

        int gridSize = 25;

        for (int x = 0;
                x <= getWidth();
                x += gridSize) {

            g.drawLine(
                    x,
                    0,
                    x,
                    getHeight());
        }

        for (int y = 0;
                y <= getHeight();
                y += gridSize) {

            g.drawLine(
                    0,
                    y,
                    getWidth(),
                    y);
        }
    }
}
