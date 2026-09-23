
import java.awt.Graphics2D;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FloorPlan {
    private final int width;
    private final int height;
    private final LocalDate dateCreated;
    private final BoothGroup boothCollection;

    public FloorPlan(int width, int height) {
        this.width = width;
        this.height = height;
        this.dateCreated = LocalDate.now();
        this.boothCollection = new BoothGroup();
    }

    // Creator + Expert: FloorPlan owns the booth collection and creates booths.
    public Booth createBooth(int x, int y, String shape, String size) {
        BoothType boothType = BoothTypeFactory.getBoothType(shape, size);
        Booth booth = new Booth(x, y, boothType);
        boothCollection.add(booth);
        return booth;
    }

    public void addBooth(Booth booth) {
        if (booth != null) {
            boothCollection.add(booth);
        }
    }

    public void draw(Graphics2D g) {
        boothCollection.draw(g);
    }

    public Iterator<Booth> iterator() {
        return new FloorPlanIterator(boothCollection);
    }

    public List<Booth> getBooths() {
        List<Booth> result = new ArrayList<>();
        Iterator<Booth> iterator = iterator();

        while (iterator.hasNext()) {
            result.add(iterator.next());
        }

        return result;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public int getBoothCount() {
        int count = 0;
        Iterator<Booth> iterator = iterator();

        while (iterator.hasNext()) {
            iterator.next();
            count++;
        }

        return count;
    }

    public BoothGroup getBoothCollection() {
        return boothCollection;
    }
}
