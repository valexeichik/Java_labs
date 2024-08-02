package mvc;
import iterator.Deque;
import visitor.MyInteger;

public class Model {
    private Deque<MyInteger> model;

    public Model() {
        model = new Deque<>();
    }

    public Deque<MyInteger> getModel() {
        return model;
    }
}
