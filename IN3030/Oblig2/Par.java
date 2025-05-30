public class Par {

    public double[][] par(double[][] A, double[][] B) {
        int n = A.length;
        int P = Math.min(Runtime.getRuntime().availableProcessors(), n);

        double[][] C = new double[n][n];

        int chunkSize = n / P;
        Thread[] threads = new Thread[P];
        for (int p = 0; p < P - 1; p++) {
            threads[p] = new Thread(new ParHelper(A, B, C, (p * chunkSize), ((p + 1) * chunkSize)));
        }
        threads[P - 1] = new Thread(new ParHelper(A, B, C, ((P - 1) * chunkSize), n));

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted");
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

    public double[][] parA(double[][] A, double[][] B) {
        double[][] AT = transponseMatrix(A);
        int n = AT.length;
        int P = Math.min(Runtime.getRuntime().availableProcessors(), n);

        double[][] C = new double[n][n];

        int chunkSize = n / P;
        Thread[] threads = new Thread[P];
        for (int p = 0; p < P - 1; p++) {
            threads[p] = new Thread(new ParAHelper(AT, B, C, (p * chunkSize), ((p + 1) * chunkSize)));
        }
        threads[P - 1] = new Thread(new ParAHelper(AT, B, C, ((P - 1) * chunkSize), n));

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted");
            }
        }

        return C;

    }

    public double[][] parB(double[][] A, double[][] B) {
        double[][] BT = transponseMatrix(B);
        int n = A.length;
        int P = Math.min(Runtime.getRuntime().availableProcessors(), n);

        double[][] C = new double[n][n];

        int chunkSize = n / P;
        Thread[] threads = new Thread[P];
        for (int p = 0; p < P - 1; p++) {
            threads[p] = new Thread(new ParBHelper(A, BT, C, (p * chunkSize), ((p + 1) * chunkSize)));
        }
        threads[P - 1] = new Thread(new ParBHelper(A, BT, C, ((P - 1) * chunkSize), n));

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted");
            }
        }

        return C;

    }
}
