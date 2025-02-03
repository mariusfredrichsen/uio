



public class A3Helper implements Runnable {
    int start;
    int end;
    int[] a;
    A3 a3;
    int k;

    public A3Helper(int start, int end, int[] a, int k, A3 a3) {
        this.start = start;
        this.end = end;
        this.a = a;
        this.a3 = a3;
        this.k = k;
    }

    public void run() {

        for (int i = start; i < end; i++) {
            if (!(a[i] > a[k-1])) continue;
            a3.replaceAtIndex(i);
        }

    }
}



