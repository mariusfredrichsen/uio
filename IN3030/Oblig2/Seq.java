public class MatrixMultiplyer {
    public static double[][] seq(double[][] A, double[][] B) {
        // Assuming A and B has same dimension and is square sized 
        int n = A.length;

        double[][] C = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return C;
    }




    public static double[][] transponseMatrix(double[][] M) {
        int n = M.length;
        int m = M[0].length;

        double[][] C = new double[m][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                C[i][j] = M[j][i];
            }
        }

        return C;
    }

    public static void seqA(double[][] A, double[][] B) {
        double[][] AT = transponseMatrix(A);
        int n = AT.length;

        double[][] C = new double[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    C[i][j] = A[i][k] * B[j][k]
                }
            }
        }
    }
}
