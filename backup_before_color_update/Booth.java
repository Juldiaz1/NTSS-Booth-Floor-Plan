import java.awt.Color;
import java.awt.Graphics2D;

public class Booth extends BoothComponent {
    private final int xLocation;
    private final int yLocation;
    private final BoothType boothType;
    private final Color color;
    private final boolean threeD;

    public Booth(
            int xLocation,
            int yLocation,
            BoothType boothType) {
        this(
                xLocation,
                yLocation,
                boothType,
                boothType.getColor(),
                false);
    }

    public Booth(
            int xLocation,
            int yLocation,
            BoothType boothType,
            Color color,
            boolean threeD) {
        this.xLocation = xLocation;
        this.yLocation = yLocation;
        this.boothType = boothType;
        this.color = color == null
                ? boothType.getColor()
                : color;
        this.threeD = threeD;
    }

    @Override
    public void draw(Graphics2D g) {
        boothType.draw(
                g,
                xLocation,
                yLocation,
                color,
                threeD);
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

    public String getShape() {
        return boothType.getShape();
    }

    public String getSize() {
        return boothType.getSize();
    }

    public double getPrice() {
        return boothType.getPrice();
    }

    public String getImage() {
        return boothType.getImage();
    }

    public int getxLocation() {
        return xLocation;
    }

    public int getyLocation() {
        return yLocation;
    }

    public Color getColor() {
        return color;
    }

    public boolean isThreeD() {
        return threeD;
    }
}