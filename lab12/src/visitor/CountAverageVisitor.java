package visitor;

interface Visitor {
    void visitMyInteger(MyInteger element);
}

public class CountAverageVisitor implements Visitor {
    private int count;
    private Integer sum;

    public CountAverageVisitor() {
        count = 0;
        sum = 0;
    }
    @Override
    public void visitMyInteger(MyInteger element) {
        sum += element.getValue();
        count++;
    }

    public double countAverage() { return (double)sum / count; }
}
