package visitor;

interface Element {
    void accept(Visitor visitor);
}

public class MyInteger implements Element {
    private Integer value;
    public MyInteger(Integer value) { this.value = value; }

    public Integer getValue() { return value; }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitMyInteger(this);
    }
}
