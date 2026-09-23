
import java.awt.Color;
import java.awt.Graphics2D;

public class Booth extends BoothComponent {
    private final int xLocation;
    private final int yLocation;
    private final BoothType boothType;

    public Booth(int xLocation, int yLocation, BoothType boothType) {
        this.xLocation = xLocation;
        this.yLocation = yLocation;
        this.boothType = boothType;
    }

    @Override
    public void draw(Graphics2D g) {
        boothType.draw(g, xLocation, yLocation);
    }

    @Override
    public int getX() {
        return xLocation;
    }

    @Override
    public int getY() {
        return yLocation;
    }

    @Override
    public int getWidth() {
        return boothType.getWidth();
    }

    @Override
    public int getHeight() {
        return boothType.getHeight();
    }

    public BoothType getBoothType() {
        return boothType;
    }

    public int getxLocation() {
        return xLocation;
    }

    public int getyLocation() {
        return yLocation;
    }

    public String getShape() {
        return boothType.getShape();
    }

    public String getSize() {
        return boothType.getSize();
    }

    public float getPrice() {
        return boothType.getPrice();
    }

    public String getImage() {
        return boothType.getImage();
    }

    public boolean contains(int mouseX, int mouseY) {
        if ("circle".equalsIgnoreCase(boothType.getShape())) {
            double centerX = xLocation + getWidth() / 2.0;
            double centerY = yLocation + getHeight() / 2.0;
            double radiusX = getWidth() / 2.0;
            double radiusY = getHeight() / 2.0;
            double dx = (mouseX - centerX) / radiusX;
            double dy = (mouseY - centerY) / radiusY;
            return dx * dx + dy * dy <= 1.0;
        }

        return mouseX >= xLocation
                && mouseX <= xLocation + getWidth()
                && mouseY >= yLocation
                && mouseY <= yLocation + getHeight();
    }
}
