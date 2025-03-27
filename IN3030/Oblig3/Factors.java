import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReentrantLock;

public class Factors {
    int k;
    long newBase, lowestPrime;
    ReentrantLock lock = new ReentrantLock();

    public Factors(int k) {
        this.k = k;
    }

    public HashMap<Long, ArrayList<Long>> findFactors(int n, long base, int[] primes) {
        HashMap<Long, ArrayList<Long>> allFactors = new HashMap<>();

        long newBase;

        int test = 0;
        for (long b = base - 1; b > base - n - 1; b--) {
            System.out.println(++test);
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

        CyclicBarrier barrier = new CyclicBarrier(k, new Runnable() {
            @Override
            public void run() {
                while (newBase % lowestPrime == 0) {
                    newBase /= lowestPrime;
                }
            }
        });

        int test = 0;
        for (long b = base - 1; b > base - m - 1; b--) {
            System.out.println(++test);
            newBase = b;

            ArrayList<Long> factors = new ArrayList<>();
            allFactors.put(b, factors);

            Thread[] threads = new Thread[k];
            int n = primes.length;
            int chunk = n / k;
            for (int i = 0; i < k - 1; i++) {
                threads[i] = new Thread(new FactorHelper(i * chunk, (i + 1) * chunk, primes, barrier));
                threads[i].start();
            }
            threads[k - 1] = new Thread(new FactorHelper(k - 1 * chunk, n, primes, barrier));
            threads[k - 1].start();

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

    public class FactorHelper implements Runnable {
        int start, end;
        int[] primes;
        CyclicBarrier barrier;

        public FactorHelper(int start, int end, int[] primes, CyclicBarrier barrier) {
            this.start = start;
            this.end = end;
            this.primes = primes;
            this.barrier = barrier;
        }

        public void run() {
            try {
                System.out.println("Computing...");
                for (int i = start; i < end; i++) {
                    if (newBase % primes[i] == 0) {
                        updateLowestPrime((long) newBase);
                        break;
                    }
                }
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateLowestPrime(long prime) {
        lock.lock();
        try {
            lowestPrime = Math.min(lowestPrime, prime);
        } finally {
            lock.unlock();
        }
    }

}
