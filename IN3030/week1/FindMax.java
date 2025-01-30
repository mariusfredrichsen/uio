

public class FindMax {
    int globalMax = Integer.MIN_VALUE;
    int globalSum;

    int[] a;

    public FindMax(int[] a) {
        this.a = a;
    }

    public int findMax(int[] a) {
        int max = a[0];
        for (int num : a) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    public void findMaxPar1(int k) {
        int n = a.length;
        int tSize = n / k;

        Thread[] threads = new Thread[k];

        for (int i = 0; i < k-1; i++) {
            int start = i * tSize;
            int end = (i+1) * tSize;
            Thread t = new Thread(new Strat1(start, end, a, this));
            threads[i] = t;
        }

        Thread tmp = new Thread(new Strat1(tSize * (k-1), n, a, this));
        threads[k-1] = tmp;

        long start = System.nanoTime(); 
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                System.out.println("Something went wrong");
                return;
            }
        }

        long end = System.nanoTime();
        System.out.println("Time spent: " + ((end - start) / 1000000.0));
        System.out.println("Global max: " +  globalMax);
    }

    public void findSum(int k) {
        int n = a.length;
        int tSize = n / k;

        Thread[] threads = new Thread[k];

        for (int i = 0; i < k-1; i++) {
            int start = i * tSize;
            int end = (i+1) * tSize;
            Thread t = new Thread(new Strat2(start, end, a, this));
            threads[i] = t;
        }

        Thread tmp = new Thread(new Strat2(tSize * (k-1), n, a, this));
        threads[k-1] = tmp;

        long start = System.nanoTime(); 
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                System.out.println("Something went wrong");
                return;
            }
        }

        long end = System.nanoTime();
        System.out.println("Time spent finding Sum: " + ((end - start) / 1000000.0));
        System.out.println("Global Sum: " +  globalSum);
    }

    synchronized void updateMax(int num) {
        if (globalMax < num) globalMax = num;
    }

    synchronized void updateSum(int sum) {
        globalSum += sum;
    }

    public class Strat1 implements Runnable {
        private int start;
        private int end;
        FindMax fm;
        int[] a;
    
        public Strat1(int start, int end, int[] a, FindMax fm) {
            this.start = start;
            this.end = end;
            this.fm = fm;
            this.a = a;
        }
    
        @Override
        public void run() {
            int localMax = a[start];
    
            for (int i = start+1; i < end; i++) {
                int num = a[i];
                if (num > localMax) localMax = num;
            }
    
            fm.updateMax(localMax);
        }
    }

    public class Strat2 implements Runnable {
        private int start;
        private int end;
        FindMax fm;
        int[] a;
        int localSum = 0;
    
        public Strat2(int start, int end, int[] a, FindMax fm) {
            this.start = start;
            this.end = end;
            this.fm = fm;
            this.a = a;
        }
    
        @Override
        public void run() {
    
            for (int i = start; i < end; i++) {
                localSum += a[i];
            }
    
            fm.updateSum(localSum);
        }
    }
}
