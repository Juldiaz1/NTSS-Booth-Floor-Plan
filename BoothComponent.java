
import java.awt.Graphics2D;

public abstract class BoothComponent {
    public abstract void draw(Graphics2D g);
    public abstract int getX();
    public abstract int getY();
    public abstract int getWidth();
    public abstract int getHeight();

    public void add(BoothComponent component) {
        throw new UnsupportedOperationException("Cannot add to a leaf.");
    }

    public void remove(BoothComponent component) {
        throw new UnsupportedOperationException("Cannot remove from a leaf.");
    }

    public BoothComponent get(int index) {
        throw new UnsupportedOperationException("Cannot get children from a leaf.");
    }
}
