
import java.util.Iterator;

public class FloorPlanIterator implements Iterator<Booth> {
    private final BoothIterator iterator;

    public FloorPlanIterator(BoothGroup root) {
        iterator = new BoothIterator(root);
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public Booth next() {
        return iterator.next();
    }
}
