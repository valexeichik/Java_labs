// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        try {
            Integer[] elements1 = {8, 3, 10, 1, 6, 4, 7, 14, 13};
            BinaryTree<Integer> tree = new BinaryTree<>(elements1);


            tree.lnrTraversal();
        }
        catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
