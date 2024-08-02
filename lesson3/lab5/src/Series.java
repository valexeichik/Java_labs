import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
abstract class Series {
    double firstElement;
    int numberOfElements;
    double delta;
    Series() { };
    Series(double firstElement, int numberOfElements, double delta){
        if (numberOfElements < 0) {
            throw new IllegalArgumentException("Error! Check the number of elements!s");
        }
        this.firstElement = firstElement;
        this.numberOfElements = numberOfElements;
        this.delta = delta;
    }

    public String toString() {
        StringBuffer elements = new StringBuffer();
        for (int i = 1; i <= numberOfElements; i++) {
            elements.append(String.format("%.1f ",calculateElement(i)));
            //elements.append(" ");
        }
        return elements.toString();
    }
    abstract double calculateElement(int i);
    public double seriesSum(){
        double sum = 0;
        for (int i = 1; i <= numberOfElements; i++){
            sum += calculateElement(i);
        }
        return sum;
    }

    public void printInFile(String path) throws IOException {
        FileWriter writer = new FileWriter(path, false);
        String ser = toString();
        writer.write("The elements of the series: ");
        writer.write('\n');
        writer.write(ser);
        writer.write('\n');
        writer.write("Sum is " + seriesSum());
        writer.close();
    }
}