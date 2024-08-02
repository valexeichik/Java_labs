import java.util.StringTokenizer;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        try {
            if (args.length != 2) {
                throw new IllegalArgumentException("Invalid number of arguments!");
            }

            int x = Integer.parseInt(args[0]);
            System.out.printf("The result = %s", result(x, args[1]));
        }
        catch(NumberFormatException ex) {
            System.out.println("Check argument!");
        }
        catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }
// (gdd) (gdgd()) ( (( )
    static int result(int x, String expr) throws NumberFormatException {
        boolean flag = true;
        int resultOfExpression = 0;
        StringTokenizer tokenizer = new StringTokenizer(expr, "+-", true);

        while (tokenizer.hasMoreTokens()) {
            String currentToken = tokenizer.nextToken();
            switch(currentToken) {
                case "+": {
                    flag = true;
                    break;
                }
                case "-": {
                    flag = false;
                    break;
                }
                case "x":
                case "X": {
                    if (flag) {
                        resultOfExpression += x;
                    } else {
                        resultOfExpression -= x;
                    }
                    break;
                }
                default: {
                    if (flag) {
                        resultOfExpression += Integer.parseInt(currentToken);
                    } else {
                        resultOfExpression -= Integer.parseInt(currentToken);
                    }
                }
            }
        }

        return resultOfExpression;
    }
}