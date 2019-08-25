package testing;


/**
 * Utility tools to debug 3D-matrices.
 * @param <T> The data type of the matrix.
 */
public class DebugMatrix<T> {

    /**
     * Prints any 3 dimensional matrix occupation ('X' if occupied, '_' if not) to the console in a 2D form. (3rd dimension will be truncated!)
     * @param matrix The matrix to debug.
     */
    public void debugOccupation3DMatrixIn2D(T [][][] matrix){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print((matrix[i][j][0] != null ? "X" :"_" )+ " ");
            }
        }
    }
}