import java.awt.Color;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(
            String[] args) {

        SwingUtilities.invokeLater(() -> {

            FloorPlanController controller =
                    new FloorPlanController();

            createDemoBooths(
                    controller);

            NTSSStaffUI ui =
                    new NTSSStaffUI(
                            controller);

            ui.setVisible(true);
        });
    }

    private static void createDemoBooths(
            FloorPlanController controller) {

        try {

            controller.placeBooth(
                    80,
                    90,
                    "circle",
                    "small",
                    new Color(
                            75,
                            130,
                            220));

            controller.placeBooth(
                    210,
                    85,
                    "square",
                    "medium",
                    new Color(
                            70,
                            170,
                            105));

            controller.placeBooth(
                    365,
                    90,
                    "rectangle",
                    "large",
                    new Color(
                            235,
                            145,
                            55));

            controller.placeBooth(
                    100,
                    260,
                    "square",
                    "small",
                    new Color(
                            215,
                            75,
                            75));

            controller.placeBooth(
                    245,
                    250,
                    "circle",
                    "medium",
                    new Color(
                            135,
                            90,
                            190));

            controller.placeBooth(
                    425,
                    270,
                    "rectangle",
                    "medium",
                    new Color(
                            55,
                            165,
                            165));

            controller.placeBooth(
                    650,
                    130,
                    "circle",
                    "large",
                    new Color(
                            215,
                            175,
                            45));

            controller.placeBooth(
                    750,
                    355,
                    "square",
                    "medium",
                    new Color(
                            220,
                            105,
                            165));

        } catch (
                IllegalArgumentException exception) {

            System.out.println(
                    "Demo booth error: "
                            + exception.getMessage());
        }
    }
}
