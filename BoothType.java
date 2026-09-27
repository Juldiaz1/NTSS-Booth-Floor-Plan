import java.awt.Color;
import java.awt.Graphics2D;

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

    public void draw(
            Graphics2D g,
            int x,
            int y) {

        draw(
                g,
                x,
                y,
                color);
    }

    /*
     * Supports booth-specific colors while keeping
     * BoothType shared by the Flyweight Factory.
     */
    public void draw(
            Graphics2D g,
            int x,
            int y,
            Color overrideColor) {

        Color oldColor =
                g.getColor();

        Color fillColor =
                overrideColor != null
                        ? overrideColor
                        : color;

        g.setColor(fillColor);

        if ("circle".equalsIgnoreCase(
                shape)) {

            g.fillOval(
                    x,
                    y,
                    width,
                    height);

            g.setColor(
                    new Color(
                            45,
                            45,
                            45));

            g.drawOval(
                    x,
                    y,
                    width,
                    height);

        } else {

            g.fillRoundRect(
                    x,
                    y,
                    width,
                    height,
                    12,
                    12);

            g.setColor(
                    new Color(
                            45,
                            45,
                            45));

            g.drawRoundRect(
                    x,
                    y,
                    width,
                    height,
                    12,
                    12);
        }

        g.setColor(Color.WHITE);

        String text =
                shape.substring(0, 1)
                        .toUpperCase()
                        + shape.substring(1);

        g.drawString(
                text,
                x + 8,
                y + height / 2);

        g.setColor(oldColor);
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
}
