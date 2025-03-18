import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Main {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Program must have 2 arguments, 0: base-number, 1: number-of-threads");
            System.exit(1);
        }
        int n = Integer.parseInt(args[0]);
        int N = n*n;

        System.out.println(N);
        int k = Integer.parseInt(args[1]);

        Oblig3Precode precode = new Oblig3Precode(N);

        System.out.println("Seq");

        // Seq
        SieveOfEratosthenes soe = new SieveOfEratosthenes(N);
        int[] primes = soe.getPrimes();
        HashMap<Integer, ArrayList<Integer>> allFactors = findFactors(100, N, primes);

        for (Map.Entry<Integer, ArrayList<Integer>> entry : allFactors.entrySet()) {
            int base = entry.getKey();
            ArrayList<Integer> factors = entry.getValue();
            checkFactors(base, factors);
            for (int factor : factors) {
                precode.addFactor(base, factor);
            }
        }

        precode.writeFactors();

        /*System.out.println("Par");
        // Par
        SOEPar soeP = new SOEPar(n, k);
        primes = soeP.getPrimes();
        factors = findFactors(n, primes);

        checkFactors(n, factors);*/
    }

    public static HashMap<Integer, ArrayList<Integer>> findFactors(int n, int base, int[] primes) {
        HashMap<Integer, ArrayList<Integer>> allFactors = new HashMap<>();

        int newBase;

        int test = 0;
        for (int b = base-1; b > base-n-1; b--) {
            System.out.println(++test);
            newBase = b;
        
            ArrayList<Integer> factors = new ArrayList<>();
            allFactors.put(b, factors);

            int p = 0;
            while (true) {
                int prime = primes[p];
                if (prime == newBase) {
                    factors.add(prime);
                    break;
                }
                if (newBase % prime == 0) {
                    factors.add(prime);
                    newBase /= prime;
                    p = 0;
                    continue;
                }
                p++;
            }
        }

        return allFactors;
    }

    public static void checkFactors(int base, ArrayList<Integer> factors) {
        int sum = 1;
        for (int factor : factors) {
            sum *= factor;
        }
        assert sum == base;
    }
}
