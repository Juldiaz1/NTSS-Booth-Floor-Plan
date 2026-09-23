
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FloorPlanController controller = new FloorPlanController();
            NTSSStaffUI ui = new NTSSStaffUI(controller);
            ui.setVisible(true);
        });
    }
}
