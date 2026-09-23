
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class BoothIterator implements Iterator<Booth> {
    private final List<Booth> booths = new ArrayList<>();
    private int index = 0;

    public BoothIterator(BoothGroup group) {
        collect(group);
    }

    private void collect(BoothComponent component) {
        if (component instanceof Booth) {
            booths.add((Booth) component);
            return;
        }

        if (component instanceof BoothGroup) {
            BoothGroup group = (BoothGroup) component;

            for (BoothComponent child : group.getChildren()) {
                collect(child);
            }
        }
    }

    @Override
    public boolean hasNext() {
        return index < booths.size();
    }

    @Override
    public Booth next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        return booths.get(index++);
    }
}
