import java.util.Arrays;


public class A3 {
    int[] a;
    int k;
    int p;

    public A3(int[] a, int k, int p) {
        this.a = a;
        this.k = k;
        this.p = p;
    }
    

    public int[] sortArray() {
        int n = a.length;
        int[] biggest = new int[k];

        for (int i = 1; i < k; i++) {
            int j = i;
            while (j > 0 && a[j] > a[j-1]) {
                int tmp = a[j-1];
                a[j-1] = a[j];
                a[j] = tmp;
                j--;
            }
        }

        int chunk = n / p;
        Thread[] threads = new Thread[p];
        for (int q = 0; q < p-1; q++) {
            threads[q] = new Thread(new A3Helper(q * chunk, (q+1) * chunk, a, k, this));
        }
        threads[p-1] = new Thread(new A3Helper((p-1) * chunk, n, a, k, this));

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                System.out.println("Something went wrong");
                return new int[1];
            }
        }

        for (int l = 0; l < k; l++) {
            biggest[l] = a[l];
        }

        return biggest;
    }

    public synchronized void replaceAtIndex(int i) {
        int j = k-1;
        if (a[i] > a[j]) {
            int tmp = a[j];
            a[j] = a[i];
            a[i] = tmp;

            while (j > 0 && a[j] > a[j-1]) {
                tmp = a[j-1];
                a[j-1] = a[j];
                a[j] = tmp;
                j--;
            }
        }
    }
}
