import java.util.Scanner;

public class Sudoku_Validation{
    public static void main(String[] Args){
        System.out.println("Enter a sudoku puzzle to be checked:");
        int[][] sudoku = createArray();
        System.out.println("The given sudoku is: " + isValid(sudoku));
    }

    public static int[][] createArray(){
        Scanner scanner = new Scanner(System.in);
        String[] inputString = new String[9];
        for(int i = 0; i < 9; i++){
            inputString[i] = scanner.nextLine();
        }

        int sudokuLength = 9;
        int[][] sudoku = new int[sudokuLength][sudokuLength];
        for(int i = 0; i < sudokuLength; i++){
            if(inputString[i].length() != sudokuLength){
                System.out.println("Invalid input");
                System.exit(0);
            }
            for(int j = 0; j < sudokuLength; j++){
                sudoku[i][j] = Character.getNumericValue(inputString[i].charAt(j));
            }
        }
        scanner.close(); 
        return sudoku;
    }

    public static boolean isValid(int[][] sudoku){
        if(checkRow(sudoku) && checkColumn(sudoku) && checkSubGrid(sudoku)){
            return true;
        }
        return false;
    }


    public static boolean checkRow(int[][]sudoku){
        int sudokuLength = sudoku[0].length;
        for(int i = 0; i < sudokuLength; i++){
            boolean[] seen = new boolean[sudokuLength + 1];
            for(int j = 0; j < sudokuLength; j++){
                int currentNum = sudoku[i][j];
                if(currentNum < 1 || currentNum > 9 || seen[currentNum]) return false;
                seen[currentNum] = true;
            }
        }
        return true;
    }

    public static boolean checkColumn(int[][]sudoku){
        int sudokuLength = sudoku[0].length;
        for(int i = 0; i < sudokuLength; i++){
            boolean[] seen = new boolean[sudokuLength + 1];
            for(int j = 0; j < sudokuLength; j++){
                int currentNum = sudoku[j][i];
                if(currentNum < 1 || currentNum > 9 || seen[currentNum]) return false;
                seen[currentNum] = true;
            }
        }
        return true;
    }

    public static boolean checkSubGrid(int[][]sudoku){
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