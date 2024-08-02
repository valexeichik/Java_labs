import mvc.Controller;
import mvc.Set;
import mvc.View;

public class Main {
    public static void main(String[] args) {
        View view = new View("Set");
        Set set = new Set(-3);
        Controller controller = new Controller(set, view);
        controller.initController();

        //model (passive) - Set
        //view - View
        //controller - Controller
    }
}