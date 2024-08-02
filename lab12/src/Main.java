import mvc.Controller;
import mvc.Model;
import mvc.View;

public class Main {
    public static void main(String[] args) {
        View view = new View("Lab12");
        Model model = new Model();
        Controller controller = new Controller(model, view);
        controller.initController();
    }
}