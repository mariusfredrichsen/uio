public class Seq {
    public static double[][] seq(double[][] A, double[][] B) {
        // Assuming A and B has same dimension and is square sized
        int n = A.length;

        double[][] C = new double[n][n];

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return C;
    }

    public static double[][] transponseMatrix(double[][] M) {
        int n = M.length;

        double[][] C = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = M[j][i];
            }
        }

        return C;
    }

    public static double[][] seqA(double[][] A, double[][] B) {
        double[][] AT = transponseMatrix(A);
        int n = AT.length;

        double[][] C = new double[n][n];

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    C[i][j] += AT[k][i] * B[k][j];
                }
            }
        }

        return C;
    }

    public static double[][] seqB(double[][] A, double[][] B) {
        double[][] BT = transponseMatrix(B);
        int n = BT.length;

        double[][] C = new double[n][n];

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    C[i][j] += A[i][k] * BT[j][k];
                }
            }
        }

        return C;
    }
}
