import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            double[][] matrix = readMatrixFromFile("input1.txt");
            if (findDeterminant(matrix, matrix.length) == 0) {
                throw new DataInvalidException("Error! The determinant of matrix = 0!");
            }

            printMatrixInFile(inverseMatrix(matrix, matrix.length));
        }
        catch (DataInvalidException ex) {
            System.out.println(ex.getMessage());
        }
        catch (InputMismatchException ex) {
            System.out.println("Error! Check format of the elements!");
        }
        catch (NoSuchElementException ex) {
            System.out.println("Error! There is not enough matrix elements!");
        }
        catch (IOException ex) {
            System.out.println("Error! Check your files!");
        }
    }

    static double[][] getMatrixWithoutRowAndCol(double[][] matrix, int size, int row, int col) {
        double[][] newMatrix = new double[size - 1][size - 1];
        int offsetRow = 0;
        int offsetCol = 0;

        for (int i = 0; i < size - 1; i++) {
            if (i == row) {
                offsetRow = 1;
            }

            offsetCol = 0;
            for (int j = 0; j < size - 1; j++) {
                if (j == col) {
                    offsetCol = 1;
                }

                newMatrix[i][j] = matrix[i + offsetRow][j + offsetCol];
            }
        }

        return newMatrix;
    }

    static double findDeterminant(double[][] matrix, int size) {
        double det = 0;
        int degree = 1;

        if (size == 1) {
            return matrix[0][0];
        }
        else if (size == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }
        else {
            for (int i = 0; i < size; i++) {
                double[][] newMatrix = getMatrixWithoutRowAndCol(matrix, size, 0, i);
                det += degree * matrix[0][i] * findDeterminant(newMatrix, size - 1);
                degree = -degree;
            }
        }

        return det;
    }

    static double[][] inverseMatrix(double[][] matrix, int size) {
        double[][] newMatrix = new double[size][size];
        int degree = 1;
        double det = findDeterminant(matrix, matrix.length);

        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                if ((i + j) % 2 == 0) {
                    degree = 1;
                }
                else {
                    degree = -1;
                }

                double algComplement = findDeterminant(getMatrixWithoutRowAndCol(matrix, matrix.length, i, j), size - 1);
                newMatrix[j][i] = degree * algComplement / det;
            }
        }

        return newMatrix;
    }

    static double[][] readMatrixFromFile(String path) throws FileNotFoundException, InputMismatchException, NoSuchElementException, DataInvalidException {
        Scanner input = new Scanner(new FileReader(path));
        int n = input.nextInt();
        if (n <= 0) {
            throw new DataInvalidException("Error! Check matrix size!");
        }

        double[][] matrix = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = input.nextInt();
            }
        }

        if (input.hasNext()) {
            throw new DataInvalidException("Error! There is too much information in the file!");
        }

        return matrix;
    }
    static void printMatrixInFile(double[][] matrix) throws IOException {
        FileWriter writer = new FileWriter("output.txt", false);

        for (double[] row : matrix) {
            for (double element : row ) {
                if (element == 0) {
                    element = 0;
                }
                writer.write(String.format("%6.2f", element));
                writer.write(" ");
            }
            writer.write("\n");
        }

        writer.close();
    }
}

class DataInvalidException extends Exception {
    DataInvalidException(String msg) {
        super(msg);
    }
}