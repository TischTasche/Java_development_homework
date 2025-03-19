import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Sudoku_Validation {
    public static void main(String[] Args) {
        System.out.println("Enter Sudoku puzzles separated by two consecutive line breaks. Press Enter twice to finish input:");
        List<int[][]> sudokuPuzzles = createArrays(readInput());

        for (int i = 0; i < sudokuPuzzles.size(); i++) {
            System.out.println("The given Sudoku puzzle " + (i + 1) + " is: " + (isValid(sudokuPuzzles.get(i)) ? "Valid" : "Invalid"));
        }
    }

    public static String readInput() {
        Scanner scanner = new Scanner(System.in);
        String input = new String();
        int emptyLineCount = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.isEmpty()) {
                emptyLineCount++;
                if (emptyLineCount == 2) {
                    break;
                }
                input += "\n";
            } else {
                emptyLineCount = 0;
                input += line + "\n";
            }
        }
        scanner.close();
        return input.toString();
    }

    public static List<int[][]> createArrays(String input) {
        String[] sudokuStrings = input.split("\n\n");
        List<int[][]> sudokuPuzzles = new ArrayList<>();
        int sudokuLength = 9;
        for (String sudokuString : sudokuStrings) {
            String[] lines = sudokuString.split("\n");
            int[][] sudoku = new int[sudokuLength][sudokuLength];

            if (lines.length != sudokuLength) {
                System.out.println("Invalid input");
                System.exit(0);
            }

            for (int i = 0; i < sudokuLength; i++) {
                if (lines[i].length() != sudokuLength) {
                    System.out.println("Invalid input");
                    System.exit(0);
                }
                for (int j = 0; j < sudokuLength; j++) {
                    sudoku[i][j] = Character.getNumericValue(lines[i].charAt(j));
                }
            }
            sudokuPuzzles.add(sudoku);
        }
        return sudokuPuzzles;
    }

    public static boolean isValid(int[][] sudoku) {
        return checkRow(sudoku) && checkColumn(sudoku) && checkSubGrid(sudoku);
    }

    public static boolean checkRow(int[][] sudoku) {
        int sudokuLength = sudoku[0].length;
        for (int i = 0; i < sudokuLength; i++) {
            boolean[] seen = new boolean[sudokuLength + 1];
            for (int j = 0; j < sudokuLength; j++) {
                int currentNum = sudoku[i][j];
                if (currentNum < 1 || currentNum > 9 || seen[currentNum]) return false;
                seen[currentNum] = true;
            }
        }
        return true;
    }

    public static boolean checkColumn(int[][] sudoku) {
        int sudokuLength = sudoku[0].length;
        for (int i = 0; i < sudokuLength; i++) {
            boolean[] seen = new boolean[sudokuLength + 1];
            for (int j = 0; j < sudokuLength; j++) {
                int currentNum = sudoku[j][i];
                if (currentNum < 1 || currentNum > 9 || seen[currentNum]) return false;
                seen[currentNum] = true;
            }
        }
        return true;
    }

    public static boolean checkSubGrid(int[][] sudoku) {
        int sudokuLength = sudoku.length;
        int subGridSize = 3;
        for (int row = 0; row < sudokuLength; row += subGridSize) {
            for (int col = 0; col < sudokuLength; col += subGridSize) {
                boolean[] seen = new boolean[sudokuLength + 1];
                for (int i = 0; i < subGridSize; i++) {
                    for (int j = 0; j < subGridSize; j++) {
                        int currentNum = sudoku[row + i][col + j];
                        if (currentNum < 1 || currentNum > 9 || seen[currentNum]) return false;
                        seen[currentNum] = true;
                    }
                }
            }
        }
        return true;
    }
}