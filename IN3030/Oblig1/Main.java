import java.util.Random;
import java.util.Arrays;


public class Main {
    public static void main(String[] args) {
        int p = Runtime.getRuntime().availableProcessors();
        
        double[][] times = new double[6][7];
        int j = 0;
        for (int n = 1000; n < 1000000000; n = n * 10) {
            System.out.println(String.format("N: %s", n));
            for (int i = 0; i < 7; i++) {
                double time = runSingleTest(n, 100, 16);
                times[j][i] = time;
            }
            j++;
        }

        for (double[] l : times) {
            Arrays.sort(l);
            System.out.println(l[3]);
        }
    }

    public static double runSingleTest(int n, int k, int p) {
        int[] a = createArray(n);

        /* A1 a1 = new A1(); */
        /* A2 a2 = new A2(); */
        A3 a3 = new A3(a.clone(), k, p);

        /* double startA1 = System.nanoTime();
        int[] biggestA1 = a1.sortArray(a.clone(), k);
        double endA1 = System.nanoTime(); */

        /* double startA2 = System.nanoTime();
        int[] biggestA2 = a2.sortArray(a.clone(), k);
        double endA2 = System.nanoTime(); */

        double startA3 = System.nanoTime();
        int[] biggestA3 = a3.sortArray();
        double endA3 = System.nanoTime();

        /* for (int i = 0; i < k; i++) {
            assert biggestA1[i] == biggestA2[i] && biggestA1[i] == biggestA3[i];
        } */

        /* System.out.println(String.format("\nN = %s, K = %s", n, k));
        System.out.println(String.format("A1 time: %s", (endA1 - startA1) / 1000000.0)); */
       /*  System.out.println(String.format("A2 time: %s", (endA2 - startA2) / 1000000.0));
        System.out.println(String.format("A3 time: %s", (endA3 - startA3) / 1000000.0)); */

        return (endA3 - startA3) / 1000000.0;
    }

    public static int[] createArray(int size) {
        int[] a = new int[size];
        Random r = new Random();
        for (int i = 0; i < size; i++) {
            a[i] = r.nextInt(100000000);
        }
        return a;
    }
}
