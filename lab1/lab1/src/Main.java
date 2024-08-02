// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

public class Main {
    public static void main(String[] args) {
        //System.out.println("Enter values of x and eps: ")
        try {
            if (args.length != 2) {
                throw new IllegalArgumentException("Error! Invalid number of arguments!");
            }

            double x = Double.parseDouble(args[0]);
            double delta = Double.parseDouble(args[1]);

            if (delta >= 1) {
                throw new IllegalArgumentException("Error! Invalid value of delta!");
            }
            System.out.printf("Sum of the series = %.15f", sum(x, delta));
        }
        catch (NumberFormatException ex) {
            System.out.println("Error! X and delta should be numbers!");
        }
        catch (IllegalArgumentException ex){
            System.out.println(ex.getMessage());
        }
    }

    static double sum(double x, double delta) {
        double sum = 0;
        double temp = x * x * Math.pow(4.0 / 3, 6) / 6;
        int k = 0;

        while (delta <= Math.abs(temp)) {
            sum += temp;
            k++;
            temp = -temp / (2 * k + 3) / (2 * k + 2) * Math.pow(4.0 / 3, 4);
        }
        return sum;
    }

}