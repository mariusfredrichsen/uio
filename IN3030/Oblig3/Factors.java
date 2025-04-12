import java.util.ArrayList;
import java.util.HashMap;

public class Factors {
    int k;

    public Factors(int k) {
        this.k = k;
    }

    public HashMap<Long, ArrayList<Long>> findFactors(int m, long base, int[] primes) {
        HashMap<Long, ArrayList<Long>> allFactors = new HashMap<>();

        long newBase;

        for (long b = base - 1; b > base - m - 1; b--) {
            newBase = b;

            ArrayList<Long> factors = new ArrayList<>();
            allFactors.put(b, factors);

            for (int prime : primes) {
                if (prime == newBase) {
                    factors.add((long) prime);
                    newBase = 1;
                    break;
                }
                while (newBase % prime == 0) {
                    factors.add((long) prime);
                    newBase /= prime;
                }
            }
            if (newBase != 1) {
                factors.add((long) newBase);
            }
        }

        return allFactors;
    }

    public HashMap<Long, ArrayList<Long>> findFactorsPar(int m, long base, int[] primes) {
        HashMap<Long, ArrayList<Long>> allFactors = new HashMap<>();

        for (long b = base - 1; b > base - m - 1; b--) {
            ArrayList<Long> factors = new ArrayList<>();
            allFactors.put(b, factors);
        }

        long chunk = m / k;
        Thread[] threads = new Thread[k];
        for (int i = 0; i < k; i++) {
            long start = (base - m) + i * chunk;
            long end = (i == k - 1) ? base : start + chunk;
            threads[i] = new Thread(new FactorHelper(start, end, allFactors, primes));
            threads[i].start();
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {

            }
        }

        return allFactors;
    }

    public class FactorHelper implements Runnable {
        long start, end;
        HashMap<Long, ArrayList<Long>> allFactors;
        int[] primes;

        public FactorHelper(long start, long end, HashMap<Long, ArrayList<Long>> allFactors, int[] primes) {
            this.start = start;
            this.end = end;
            this.allFactors = allFactors;
            this.primes = primes;
        }

        public void run() {
            for (long b = start; b < end; b++) {
                ArrayList<Long> factors = allFactors.get(b);
                long newBase = b;

                for (int prime : primes) {
                    if (prime == newBase) {
                        factors.add((long) prime);
                        newBase = 1;
                        break;
                    }
                    while (newBase % prime == 0) {
                        factors.add((long) prime);
                        newBase /= prime;
                    }
                }
                if (newBase != 1) {
                    factors.add((long) newBase);
                }
            }
        }
    }
}
