
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class BoothGroup extends BoothComponent {
    private final List<BoothComponent> children = new ArrayList<>();

    @Override
    public void draw(Graphics2D g) {
        for (BoothComponent child : children) {
            child.draw(g);
        }
    }

    @Override
    public void add(BoothComponent component) {
        if (component != null) {
            children.add(component);
        }
    }

    @Override
    public void remove(BoothComponent component) {
        children.remove(component);
    }

    @Override
    public BoothComponent get(int index) {
        return children.get(index);
    }

    public List<BoothComponent> getChildren() {
        return children;
    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }
}
