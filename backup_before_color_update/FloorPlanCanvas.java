import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class FloorPlanCanvas extends JPanel {

    private final FloorPlanController controller;
    private final NTSSStaffUI ui;

    private boolean placementMode = false;

    private int mouseX = -1;
    private int mouseY = -1;

    public FloorPlanCanvas(
            FloorPlanController controller,
            NTSSStaffUI ui) {

        this.controller = controller;
        this.ui = ui;

        setBackground(
                new Color(248, 248, 248));

        setBorder(
                BorderFactory.createLineBorder(
                        Color.DARK_GRAY,
                        2));

        addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseClicked(
                            MouseEvent event) {

                        placeBooth(
                                event.getX(),
                                event.getY());
                    }
                });

        addMouseMotionListener(
                new MouseAdapter() {

                    @Override
                    public void mouseMoved(
                            MouseEvent event) {

                        mouseX =
                                event.getX();

                        mouseY =
                                event.getY();

                        repaint();
                    }
                });
    }

    private void placeBooth(
            int x,
            int y) {

        if (!placementMode) {
            return;
        }

        String shape =
                ui.getSelectedShape();

        String size =
                ui.getSelectedSize();

        try {

            Booth booth =
                    controller.placeBooth(
                            x,
                            y,
                            shape,
                            size);

            placementMode = false;

            ui.reportBoothPlaced(
                    booth);

            repaint();

        } catch (IllegalArgumentException exception) {

            ui.showPlacementError(
                    exception.getMessage());
        }
    }

    public void setPlacementMode(
            boolean placementMode) {

        this.placementMode =
                placementMode;

        repaint();
    }

    @Override
    protected void paintComponent(
            Graphics graphics) {

        super.paintComponent(
                graphics);

        Graphics2D g =
                (Graphics2D)
                        graphics.create();

        g.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        drawBackground(g);

        drawGrid(g);

        drawHeader(g);

        controller.getFloorPlan()
                .draw(g);

        drawBoothLabels(g);

        drawCursor(g);

        g.dispose();
    }

    private void drawBackground(
            Graphics2D g) {

        g.setColor(
                new Color(248, 248, 248));

        g.fillRect(
                0,
                0,
                getWidth(),
                getHeight());
    }

    private void drawGrid(
            Graphics2D g) {

        int gridSize = 25;

        g.setColor(
                new Color(220, 220, 220));

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

    private void drawHeader(
            Graphics2D g) {

        g.setColor(
                new Color(35, 35, 35));

        g.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16));

        g.drawString(
                "NTSS EXHIBITION AREA",
                18,
                28);

        g.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12));

        g.drawString(
                "Interactive Booth Placement Canvas",
                18,
                46);
    }

    private void drawBoothLabels(
            Graphics2D g) {

        Iterator<Booth> iterator =
                controller.getFloorPlan()
                        .iterator();

        int number = 1;

        while (iterator.hasNext()) {

            Booth booth =
                    iterator.next();

            int centerX =
                    booth.getX()
                            + booth.getWidth() / 2;

            int centerY =
                    booth.getY()
                            + booth.getHeight() / 2;

            String label =
                    "B" + number;

            g.setColor(
                    Color.WHITE);

            g.setFont(
                    new Font(
                            "Arial",
                            Font.BOLD,
                            11));

            g.drawString(
                    label,
                    centerX - 8,
                    centerY + 4);

            number++;
        }
    }

    private void drawCursor(
            Graphics2D g) {

        if (mouseX < 0
                || mouseY < 0) {

            return;
        }

        g.setStroke(
                new BasicStroke(
                        1.0f));

        g.setColor(
                new Color(80, 80, 80));

        g.drawLine(
                mouseX - 8,
                mouseY,
                mouseX + 8,
                mouseY);

        g.drawLine(
                mouseX,
                mouseY - 8,
                mouseX,
                mouseY + 8);

        g.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        11));

        g.drawString(
                "(" + mouseX + ", "
                        + mouseY + ")",
                mouseX + 10,
                mouseY - 10);
    }
}
