
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Main {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Program must have 2 arguments, 0: base-number, 1: number-of-threads");
            System.exit(1);
        }
        int n = Integer.parseInt(args[0]);
        long N = (long) n * n;

        System.out.println(N);
        int k = Integer.parseInt(args[1]);

        Oblig3Precode precode = new Oblig3Precode(N);
        int m = 100;

        System.out.println("Seq");

        // Seq
        SieveOfEratosthenes soe = new SieveOfEratosthenes(n);
        int[] primesA = soe.getPrimes();
        //System.out.println(Arrays.toString(primesA));
        HashMap<Long, ArrayList<Long>> allFactorsA = findFactors(m, N, primesA);

        for (Map.Entry<Long, ArrayList<Long>> entry : allFactorsA.entrySet()) {
            long base = entry.getKey();
            ArrayList<Long> factors = entry.getValue();
            checkFactors(base, factors);
            for (long factor : factors) {
                precode.addFactor(base, factor);
            }
        }

        precode.writeFactors();

        System.out.println("Par");
        // Par
        SOEPar soeP = new SOEPar(n, k);
        int[] primesB = soeP.getPrimes();
        //System.out.println(Arrays.toString(primesB));
        HashMap<Long, ArrayList<Long>> allFactorsB = findFactors(m, N, primesB);

        for (long base : allFactorsA.keySet()) {
            compareFactors(base, allFactorsA.get(base), allFactorsB.get(base));
        }
    }

    public static HashMap<Long, ArrayList<Long>> findFactors(int n, long base, int[] primes) {
        HashMap<Long, ArrayList<Long>> allFactors = new HashMap<>();

        long newBase;

        int test = 0;
        for (long b = base - 1; b > base - n - 1; b--) {
            System.out.println(++test);
            newBase = b;

            ArrayList<Long> factors = new ArrayList<>();
            allFactors.put(b, factors);

            int p = 0;
            while (true) {
                int prime = primes[p];
                if (prime == newBase) {
                    factors.add((long) prime);
                    break;
                }
                if (newBase % prime == 0) {
                    factors.add((long) prime);
                    newBase /= prime;
                    p = 0;
                    continue;
                }
                if (++p == primes.length) {
                    factors.add(newBase);
                    break;
                }
            }
        }

        return allFactors;
    }

    public static void checkFactors(long base, ArrayList<Long> factors) {
        long sum = 1;
        for (long factor : factors) {
            sum *= factor;
        }
        assert sum == base;
    }

    public static void compareFactors(Long p, ArrayList<Long> a, ArrayList<Long> b) {
        assert a.size() == b.size(): String.format("different size a: %s and b: %s", a.size(), b.size());
        for (int i = 0; i < a.size(); i++) {
            assert Objects.equals(a.get(i), b.get(i)): String.format("different values a: %s and b: %s, on prime: %s", a.get(i), b.get(i), p);
        }
    }
}
