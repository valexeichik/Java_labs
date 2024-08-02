public class Exponential extends Series{
    public Exponential(double firstElement, int numberOfElements, double delta) {
        super(firstElement, numberOfElements, delta);
    }
    @Override
    public double calculateElement(int i) {
        return (firstElement * Math.pow(delta,(i - 1)));
    }
}