import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class BoothType {
    private final String size;
    private final String shape;
    private final float price;
    private final String image;
    private final int width;
    private final int height;
    private final Color color;

    public BoothType(
            String size,
            String shape,
            float price,
            String image,
            int width,
            int height,
            Color color) {
        this.size = size;
        this.shape = shape;
        this.price = price;
        this.image = image;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public String getShape() {
        return shape;
    }

    public float getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Color getColor() {
        return color;
    }

    public void draw(
            Graphics2D g,
            int x,
            int y) {
        draw(
                g,
                x,
                y,
                color,
                false);
    }

    public void draw(
            Graphics2D g,
            int x,
            int y,
            Color boothColor,
            boolean threeD) {

        if (boothColor == null) {
            boothColor = color;
        }

        Shape boothShape;

        if ("circle".equalsIgnoreCase(shape)) {
            boothShape = new Ellipse2D.Double(
                    x,
                    y,
                    width,
                    height);
        } else {
            boothShape = new Rectangle(
                    x,
                    y,
                    width,
                    height);
        }

        if (threeD) {
            Color light = makeLighter(boothColor);
            Color dark = makeDarker(boothColor);

            GradientPaint gradient =
                    new GradientPaint(
                            x,
                            y,
                            light,
                            x + width,
                            y + height,
                            dark);

            g.setPaint(gradient);
            g.fill(boothShape);
            g.setColor(makeDarker(dark));
        } else {
            g.setColor(boothColor);
            g.fill(boothShape);
            g.setColor(boothColor.darker());
        }

        g.draw(boothShape);

        g.setColor(Color.BLACK);

        String label =
                capitalize(shape)
                        + " "
                        + capitalize(size);

        int textX =
                x + (width
                        - g.getFontMetrics()
                                .stringWidth(label)) / 2;

        int textY =
                y + (height
                        + g.getFontMetrics()
                                .getAscent()) / 2;

        g.drawString(
                label,
                textX,
                textY);
    }

    private Color makeLighter(Color original) {
        return new Color(
                Math.min(255, original.getRed() + 65),
                Math.min(255, original.getGreen() + 65),
                Math.min(255, original.getBlue() + 65));
    }

    private Color makeDarker(Color original) {
        return new Color(
                (int) (original.getRed() * 0.65),
                (int) (original.getGreen() * 0.65),
                (int) (original.getBlue() * 0.65));
    }

    private String capitalize(String text) {
        return text.substring(0, 1).toUpperCase()
                + text.substring(1);
    }
}