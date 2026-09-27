import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class FloorPlan implements Iterable<Booth> {
    private final int width;
    private final int height;
    private final Date dateCreated;
    private final List<Booth> booths;

    public FloorPlan(int width, int height) {
        this.width = width;
        this.height = height;
        this.dateCreated = new Date();
        this.booths = new ArrayList<>();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public int getBoothCount() {
        return booths.size();
    }

    public Booth createBooth(
            int x,
            int y,
            String shape,
            String size) {

        return createBooth(
                x,
                y,
                shape,
                size,
                Color.GRAY,
                false);
    }

    public Booth createBooth(
            int x,
            int y,
            String shape,
            String size,
            Color color,
            boolean threeD) {

        BoothType type =
                BoothTypeFactory.getBoothType(
                        shape,
                        size);

        Booth booth = new Booth(
                x,
                y,
                type,
                color,
                threeD);

        booths.add(booth);

        return booth;
    }

    public void draw(Graphics2D g) {
        for (Booth booth : booths) {
            booth.draw(g);
        }
    }

    @Override
    public Iterator<Booth> iterator() {
        return booths.iterator();
    }
}
