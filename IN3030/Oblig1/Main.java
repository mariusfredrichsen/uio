import java.util.Random;


public class Main {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int k = Integer.parseInt(args[1]);
        int p = Integer.parseInt(args[2]);
        int[] a = createArray(n);

        A1 a1 = new A1();
        A2 a2 = new A2();
        A3 a3 = new A3(a.clone(), k, p);

        double startA1 = System.nanoTime();
        int[] biggestA1 = a1.sortArray(a.clone(), k);
        double endA1 = System.nanoTime();

        double startA2 = System.nanoTime();
        int[] biggestA2 = a2.sortArray(a.clone(), k);
        double endA2 = System.nanoTime();

        double startA3 = System.nanoTime();
        int[] biggestA3 = a3.sortArray();
        double endA3 = System.nanoTime();

        for (int i = 0; i < k; i++) {
            System.out.println(String.format("A1: %s, A2: %s, A3: %s, diff (A1 - A2): %s, diff (A1 - A3): %s", biggestA1[i], biggestA2[i], biggestA3[i], (biggestA1[i] - biggestA2[i]), (biggestA1[i] - biggestA3[i])));
        }

        System.out.println(String.format("\nN = %s, K = %s", n, k));
        System.out.println(String.format("A1 time: %s", (endA1 - startA1) / 1000000.0));
        System.out.println(String.format("A2 time: %s", (endA2 - startA2) / 1000000.0));
        System.out.println(String.format("A3 time: %s", (endA3 - startA3) / 1000000.0));


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
