public class Main {
    private static final int matrixSize = 4;

    public static void main(String[] args) {

        int [][] sudo = {{0,3,4,0},{4,0,0,2},{1,0,0,3},{0,2,1,0}};


        if(analyzeSudoku(sudo)){
            System.out.println("It worked");
        }else{
            System.out.println("Did not work");
        }

        ShowSudoku(sudo);
    }


    // En la fila o Columna que se encuentre busca el numero, si lo encuentra regresa TRUE
    public static boolean currentValueInRow(int[][] sudoku, int num, int row) {
        for (int i = 0; i < matrixSize; i++) {
            if (sudoku[row][i] == num) {
                return true;
            }
        }
        return false;
    }
    public static boolean currentValueInColumn(int[][] sudoku, int num, int spine) {
        for (int i = 0; i < matrixSize; i++) {
            if (sudoku[i][spine] == num) {
                return true;
            }
        }
        return false;
    }


    /*
     * Observar que en el cuadrante no se repite el mismo numero
     * Este metodo podra server en sudokus de 6x6, 9x9 o mayores, lo unco que hay que hacer es cambiar la resta
     */
    public static boolean numberInQuadrant(int[][] sudoku, int num,int row, int spine) {
        //Me va regresar el resuido de cualquier cuadrante, para saber en que cuadrante ando (%3)
        int currentQuadrantRow = row - row % 3;
        int currentQuadrantColumn = spine - spine % 3;

        for (int i = currentQuadrantRow; i < currentQuadrantRow - 3; i++) {
            for (int f = currentQuadrantColumn; f < currentQuadrantColumn - 3; f++) {
                if (sudoku[i][f] == num) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean Location(int[][] sudoku, int num, int row, int spine){
        return  !currentValueInRow(sudoku,num,row) &&
                !currentValueInColumn(sudoku,num,spine) &&
                !numberInQuadrant(sudoku,num,row,spine);
    }

    public static boolean analyzeSudoku(int [][] sudoku){
        for(int row=0; row<matrixSize; row++){
            for (int spine=0; spine<matrixSize; spine++){
                if(sudoku[row][spine] == 0){
                    for(int numeroAValidar = 1; numeroAValidar<=matrixSize; numeroAValidar++){
                        if(Location(sudoku,numeroAValidar,row,spine)){
                            sudoku[row][spine] = numeroAValidar;
                            if(analyzeSudoku(sudoku)){
                                return true;
                            }else{
                                sudoku[row][spine]=0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return  true;
    }

    public static void ShowSudoku(int[][] sudo) {
        for (int row=0; row < sudo.length; row++) {
            System.out.print("|");
            for (int spine=0; spine< sudo[row].length; spine++) {
                System.out.print (sudo[row][spine]);
                if (spine!=sudo[row].length-1) System.out.print("\t");
            }
            System.out.println("|");
        }
    }

}