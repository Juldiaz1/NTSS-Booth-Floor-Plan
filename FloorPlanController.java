import java.awt.Color;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.swing.JOptionPane;

public class FloorPlanController {
    private final FloorPlan floorPlan;

    public FloorPlanController() {
        floorPlan = new FloorPlan(900, 550);
    }

    public FloorPlan getFloorPlan() {
        return floorPlan;
    }

    public Booth placeBooth(
            int x,
            int y,
            String shape,
            String size) {

        return placeBooth(
                x,
                y,
                shape,
                size,
                Color.GRAY,
                false);
    }

    public Booth placeBooth(
            int x,
            int y,
            String shape,
            String size,
            Color color,
            boolean threeD) {

        validateCoordinates(
                x,
                y,
                shape,
                size);

        return floorPlan.createBooth(
                x,
                y,
                shape,
                size,
                color,
                threeD);
    }

    private void validateCoordinates(
            int x,
            int y,
            String shape,
            String size) {

        BoothType type =
                BoothTypeFactory.getBoothType(
                        shape,
                        size);

        if (x < 0
                || y < 0
                || x + type.getWidth()
                        > floorPlan.getWidth()
                || y + type.getHeight()
                        > floorPlan.getHeight()) {

            throw new IllegalArgumentException(
                    "The booth does not fit inside "
                            + "the floor-plan canvas.");
        }
    }

    public boolean saveFloorPlan(File file) {
        try (PrintWriter writer =
                     new PrintWriter(
                             new FileWriter(file))) {

            writer.println(
                    "NTSS BOOTH FLOOR PLAN");

            writer.println(
                    "Date Created: "
                            + floorPlan.getDateCreated());

            writer.println(
                    "Floor Width: "
                            + floorPlan.getWidth());

            writer.println(
                    "Floor Height: "
                            + floorPlan.getHeight());

            writer.println(
                    "Booth Count: "
                            + floorPlan.getBoothCount());

            writer.println();

            Iterator<Booth> iterator =
                    floorPlan.iterator();

            int number = 1;

            while (iterator.hasNext()) {
                Booth booth = iterator.next();

                writer.println(
                        "Booth " + number);

                writer.println(
                        "Shape: "
                                + booth.getShape());

                writer.println(
                        "Size: "
                                + booth.getSize());

                writer.println(
                        "Price: "
                                + booth.getPrice());

                writer.println(
                        "X: "
                                + booth.getxLocation());

                writer.println(
                        "Y: "
                                + booth.getyLocation());

                writer.println(
                        "Image: "
                                + booth.getImage());

                writer.println(
                        "Color: RGB("
                                + booth.getColor().getRed()
                                + ","
                                + booth.getColor().getGreen()
                                + ","
                                + booth.getColor().getBlue()
                                + ")");

                writer.println(
                        "View: "
                                + (booth.isThreeD()
                                ? "3D"
                                : "2D"));

                writer.println();

                number++;
            }

            return true;

        } catch (IOException exception) {

            JOptionPane.showMessageDialog(
                    null,
                    "Could not save floor plan:\n"
                            + exception.getMessage(),
                    "Save Error",
                    JOptionPane.ERROR_MESSAGE);

            return false;
        }
    }
}
