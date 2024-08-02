public class Linear extends Series {
    public Linear(double firstElement, int numberOfElements, double delta) {
        super(firstElement, numberOfElements, delta);
    }
    @Override
    public double calculateElement(int i) {
        return (firstElement + (i - 1) * delta);
    }
}