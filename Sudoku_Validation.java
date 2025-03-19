import java.util.Scanner;

public class Sudoku_Validation{
    public static void main(String[] Args){
        int[][] sudoku = createArray();
        System.out.println(isValid(sudoku));
    }

    public static int[][] createArray(){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String inputString = input.replaceAll("\n", "");
        int sudokuLength = 9;
        int[][] sudoku = new int[sudokuLength][sudokuLength];

        for(int i = 0; i < sudokuLength; i++){
            for(int j = 0; j < sudokuLength; j++){
                int index = i * sudokuLength + j;
                sudoku[i][j] = inputString.charAt(index);
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
            boolean[] seen = new boolean[sudokuLength];
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
            boolean[] seen = new boolean[sudokuLength];
            for(int j = 0; j < sudokuLength; j++){
                int currentNum = sudoku[j][i];
                if(currentNum < 1 || currentNum > 9 || seen[currentNum]) return false;
                seen[currentNum] = true;
            }
        }
        return true;
    }

    public static boolean checkSubGrid(int[][]sudoku){
        int sudokuLength = sudoku[0].length;
        int subGridLength = 3;
        for(int col = 0; col < sudokuLength; col++){
            for(int row = 0; row < sudokuLength; row++){
                boolean[] seen = new boolean[sudokuLength];
                for(int i = 0; i < subGridLength; i ++){
                    for(int j = 0; j < subGridLength; j++){
                        int currentNum = sudoku[i + row][j + col];
                        if(currentNum < 1 || currentNum > 9 || seen[currentNum]) return false;
                        seen[currentNum] = true;
                    }
                }
            }
        }
        return true;
    }
}