import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
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

    private boolean placementMode =
            false;

    private int mouseX = -1;
    private int mouseY = -1;

    private Booth selectedBooth = null;

    private Color backgroundColor =
            new Color(
                    245,
                    247,
                    250);

    public FloorPlanCanvas(
            FloorPlanController controller,
            NTSSStaffUI ui) {

        this.controller = controller;
        this.ui = ui;

        setBackground(
                backgroundColor);

        setBorder(
                BorderFactory.createLineBorder(
                        new Color(
                                80,
                                80,
                                80),
                        2));

        addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseClicked(
                            MouseEvent event) {

                        handleClick(
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

    private void handleClick(
            int x,
            int y) {

        if (placementMode) {

            placeBooth(
                    x,
                    y);

            return;
        }

        Booth booth =
                findBoothAt(
                        x,
                        y);

        selectedBooth =
                booth;

        if (booth != null) {

            ui.showBoothSelected(
                    booth);

        } else {

            ui.showCanvasSelected();
        }

        repaint();
    }

    private void placeBooth(
            int x,
            int y) {

        String shape =
                ui.getSelectedShape();

        String size =
                ui.getSelectedSize();

        Color color =
                ui.getSelectedBoothColor();

        try {

            Booth booth =
                    controller.placeBooth(
                            x,
                            y,
                            shape,
                            size,
                            color);

            placementMode =
                    false;

            selectedBooth =
                    booth;

            ui.reportBoothPlaced(
                    booth);

            repaint();

        } catch (
                IllegalArgumentException exception) {

            ui.showPlacementError(
                    exception.getMessage());
        }
    }

    public Booth getSelectedBooth() {
        return selectedBooth;
    }

    public void setSelectedBoothColor(
            Color color) {

        if (selectedBooth == null) {
            return;
        }

        selectedBooth.setCustomColor(
                color);

        repaint();
    }

    public Booth findBoothAt(
            int x,
            int y) {

        Iterator<Booth> iterator =
                controller.getFloorPlan()
                        .iterator();

        Booth found = null;

        while (iterator.hasNext()) {

            Booth booth =
                    iterator.next();

            if (booth.contains(
                    x,
                    y)) {

                found = booth;
            }
        }

        return found;
    }

    public void setPlacementMode(
            boolean placementMode) {

        this.placementMode =
                placementMode;

        repaint();
    }

    public void setCanvasBackground(
            Color color) {

        if (color == null) {
            return;
        }

        backgroundColor =
                color;

        setBackground(
                backgroundColor);

        repaint();
    }

    public Color getCanvasBackground() {
        return backgroundColor;
    }

    public void clearSelectedBooth() {

        selectedBooth =
                null;

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
        drawTitle(g);

        controller.getFloorPlan()
                .draw(g);

        drawSelectedBooth(g);
        drawBoothLabels(g);
        drawCursor(g);

        g.dispose();
    }

    private void drawBackground(
            Graphics2D g) {

        Color topColor =
                backgroundColor.brighter();

        Color bottomColor =
                backgroundColor;

        GradientPaint paint =
                new GradientPaint(
                        0,
                        0,
                        topColor,
                        0,
                        getHeight(),
                        bottomColor);

        g.setPaint(paint);

        g.fillRect(
                0,
                0,
                getWidth(),
                getHeight());
    }

    private void drawGrid(
            Graphics2D g) {

        int gridSize = 25;

        int brightness =
                backgroundColor.getRed()
                        + backgroundColor.getGreen()
                        + backgroundColor.getBlue();

        Color gridColor;

        if (brightness > 420) {

            gridColor =
                    new Color(
                            215,
                            218,
                            222);

        } else {

            gridColor =
                    new Color(
                            110,
                            115,
                            120);
        }

        g.setColor(gridColor);

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

    private void drawTitle(
            Graphics2D g) {

        g.setColor(
                new Color(
                        35,
                        35,
                        35));

        g.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18));

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
                "Click Place Booth, then click anywhere "
                        + "inside the floor plan.",
                18,
                47);
    }

    private void drawSelectedBooth(
            Graphics2D g) {

        if (selectedBooth == null) {
            return;
        }

        int x =
                selectedBooth.getX();

        int y =
                selectedBooth.getY();

        int width =
                selectedBooth.getWidth();

        int height =
                selectedBooth.getHeight();

        g.setColor(
                new Color(
                        30,
                        30,
                        30));

        g.setStroke(
                new BasicStroke(
                        3.0f));

        g.drawRect(
                x - 4,
                y - 4,
                width + 8,
                height + 8);

        g.setStroke(
                new BasicStroke(
                        1.0f));
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

            g.setFont(
                    new Font(
                            "Arial",
                            Font.BOLD,
                            12));

            g.setColor(
                    Color.WHITE);

            int labelWidth =
                    g.getFontMetrics()
                            .stringWidth(label);

            g.drawString(
                    label,
                    centerX - labelWidth / 2,
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

        g.setColor(
                new Color(
                        70,
                        70,
                        70));

        g.setStroke(
                new BasicStroke(
                        1.0f));

        g.drawLine(
                mouseX - 7,
                mouseY,
                mouseX + 7,
                mouseY);

        g.drawLine(
                mouseX,
                mouseY - 7,
                mouseX,
                mouseY + 7);

        if (placementMode) {

            g.setFont(
                    new Font(
                            "Arial",
                            Font.BOLD,
                            11));

            g.drawString(
                    "Place here",
                    mouseX + 10,
                    mouseY - 8);
        }
    }
}
