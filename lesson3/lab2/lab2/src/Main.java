public class Main {
    public static void main(String[] args) {
        try {
            if (args.length != 1) {
                throw new IllegalArgumentException("Error! There should be one argument!");
            }

            System.out.println("Text:");
            System.out.println(args[0]);
            checkInput(args[0]);
            System.out.println("Changed text:");
            System.out.print(deleteBrackets(args[0]));
        }
        catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }

    static void checkInput(String text) {
        int countBrackets = 0;

        for (int i = 0; i < text.length(); ++i) {
            if (text.charAt(i) == '(') {
                countBrackets++;
            }
            if (text.charAt(i) == ')') {
                countBrackets--;
            }

            if (countBrackets < 0) {
                break;
            }
        }

        if (countBrackets != 0) {
            throw new IllegalArgumentException("Error! Check the brackets!");
        }
    }

    static StringBuilder deleteBrackets(String text) {
        StringBuilder result = new StringBuilder(text);
        int countOpeningBrackets = 0;
        int countClosingBrackets = 0;
        int indexOfOpeningBracket = -1;

        for (int i = 0; i < result.length(); ++i) {
            if (result.charAt(i) == '(') {
                countOpeningBrackets++;
                indexOfOpeningBracket = i;
                continue;
            }

            if (result.charAt(i) == ')') {
                countClosingBrackets++;
            }

            if (countClosingBrackets == countOpeningBrackets) {
                if (countClosingBrackets == 1) {
                    result.delete(indexOfOpeningBracket, i + 1);
                    i = indexOfOpeningBracket - 1;
                }
                countOpeningBrackets = 0;
                countClosingBrackets = 0;
            }
        }
        return result;
    }
}