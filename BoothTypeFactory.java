
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public class BoothTypeFactory {
    private static final Map<String, BoothType> BOOTH_TYPES = new HashMap<>();

    public static BoothType getBoothType(String shape, String size) {
        String key = shape.toLowerCase() + "-" + size.toLowerCase();

        if (!BOOTH_TYPES.containsKey(key)) {
            BOOTH_TYPES.put(key, createBoothType(shape, size));
        }

        return BOOTH_TYPES.get(key);
    }

    private static BoothType createBoothType(String shape, String size) {
        String normalizedShape = shape.toLowerCase();
        String normalizedSize = size.toLowerCase();

        int dimension;
        float price;

        switch (normalizedSize) {
            case "small":
                dimension = 70;
                price = 500.0f;
                break;
            case "medium":
                dimension = 100;
                price = 800.0f;
                break;
            case "large":
                dimension = 130;
                price = 1200.0f;
                break;
            default:
                throw new IllegalArgumentException("Unknown booth size: " + size);
        }

        int width = dimension;
        int height = dimension;

        if ("rectangle".equals(normalizedShape)) {
            width = dimension + 40;
            height = dimension - 20;
        }

        Color color;

        switch (normalizedShape) {
            case "circle":
                color = new Color(55, 135, 220);
                break;
            case "square":
                color = new Color(70, 170, 100);
                break;
            case "rectangle":
                color = new Color(220, 145, 55);
                break;
            default:
                throw new IllegalArgumentException(
                        "Unknown booth shape: " + shape);
        }

        String image = normalizedShape + "_" + normalizedSize + ".png";

        return new BoothType(
                normalizedSize,
                normalizedShape,
                price,
                image,
                width,
                height,
                color);
    }

    public static int getCachedTypeCount() {
        return BOOTH_TYPES.size();
    }
}
