
import java.util.ArrayList;
import java.util.Arrays;
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
        if (!(n > 16)) {
            System.out.println("First parameter must be greater than 16");
            System.exit(1);
        }
        long N = (long) n * n;

        System.out.println(N);
        int k = Integer.parseInt(args[1]);
        if (k == 0)
            k = Runtime.getRuntime().availableProcessors();
        System.out.println("Kernels: " + k);
        int m = 100;

        double start;
        double end;
        int runs = 9;
        double[][] times = new double[2][runs];

        for (int i = 0; i < runs; i++) {
            Factors f = new Factors(k);

            System.out.println("Seq");
            // Seq
            start = System.nanoTime();
            SieveOfEratosthenes soe = new SieveOfEratosthenes(n);
            int[] primesA = soe.getPrimes();
            end = System.nanoTime();
            double time = ((end - start) / 1000000.0);
            times[0][i] = time;
            // System.out.println(Arrays.toString(primesA));
            HashMap<Long, ArrayList<Long>> allFactorsA = f.findFactors(m, N, primesA);
            System.out.println(String.format("Time for Seq iteration %s: %sms", i, time));

            System.out.println("Par");
            // Par
            start = System.nanoTime();
            SOEPar soeP = new SOEPar(n, k);
            int[] primesB = soeP.getPrimes();
            end = System.nanoTime();
            time = ((end - start) / 1000000.0);
            times[1][i] = time;
            // System.out.println(Arrays.toString(primesB));
            HashMap<Long, ArrayList<Long>> allFactorsB = f.findFactorsPar(m, N, primesB);
            System.out.println(String.format("Time for Par iteration %s: %sms", i, time));

            for (long base : allFactorsA.keySet()) {
                compareFactors(base, allFactorsA.get(base), allFactorsB.get(base));
            }

            Oblig3Precode precode = new Oblig3Precode(N);

            for (Map.Entry<Long, ArrayList<Long>> entry : allFactorsA.entrySet()) {
                long base = entry.getKey();
                ArrayList<Long> factors = entry.getValue();
                checkFactors(base, factors);
                for (long factor : factors) {
                    precode.addFactor(base, factor);
                }
            }

            precode.writeFactors();
        }

        for (double[] t : times) {
            Arrays.sort(t);
        }

        System.out.println(String.format("Median time for Seq: %sms", times[0][runs / 2]));
        System.out.println(String.format("Median time for Par: %sms", times[1][runs / 2]));

    }

    public static void checkFactors(long base, ArrayList<Long> factors) {
        long sum = 1;
        for (long factor : factors) {
            sum *= factor;
        }
        assert sum == base : String.format("Factorizing wrong base: %s != sum: %s", base, sum);
    }

    public static void compareFactors(Long p, ArrayList<Long> a, ArrayList<Long> b) {
        assert a.size() == b.size() : String.format("different size a: %s and b: %s", a.size(), b.size());
        for (int i = 0; i < a.size(); i++) {
            assert Objects.equals(a.get(i), b.get(i))
                    : String.format("different values a: %s and b: %s, on prime: %s", a.get(i), b.get(i), p);
        }
    }
}
