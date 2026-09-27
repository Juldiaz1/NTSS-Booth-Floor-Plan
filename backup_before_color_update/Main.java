import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            FloorPlanController controller =
                    new FloorPlanController();

            /*
             * Demo layout so the application opens looking like
             * an actual floor-plan system instead of an empty canvas.
             *
             * Creator Pattern:
             * FloorPlanController asks FloorPlan to create booths.
             *
             * Flyweight Pattern:
             * BoothTypeFactory reuses shared booth types.
             */
            createDemoBooths(controller);

            NTSSStaffUI ui =
                    new NTSSStaffUI(controller);

            ui.setVisible(true);
        });
    }

    private static void createDemoBooths(
            FloorPlanController controller) {

        try {
            controller.placeBooth(
                    70, 70, "circle", "small");

            controller.placeBooth(
                    190, 65, "square", "medium");

            controller.placeBooth(
                    330, 70, "rectangle", "large");

            controller.placeBooth(
                    90, 220, "square", "small");

            controller.placeBooth(
                    220, 210, "circle", "medium");

            controller.placeBooth(
                    390, 230, "rectangle", "medium");

            controller.placeBooth(
                    570, 120, "circle", "large");

            controller.placeBooth(
                    650, 300, "square", "medium");

        } catch (IllegalArgumentException exception) {

            System.out.println(
                    "Demo booth could not be placed: "
                            + exception.getMessage());
        }
    }
}
