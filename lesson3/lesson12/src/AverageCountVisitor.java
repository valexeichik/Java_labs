public class AverageCountVisitor implements Visitor {
    Integer size = 0;
    Integer sum = 0;
    @Override
    public void visitCustomInteger(CustomInteger element) {
        size++;
        sum += element.value;
    }

    double countAverage() {
        return (double)sum / size;
    }
}
