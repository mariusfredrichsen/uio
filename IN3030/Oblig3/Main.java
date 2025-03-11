import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Program must have 2 arguments, 0: base-number, 1: number-of-threads");
            System.exit(1);
        }
        int n = Integer.parseInt(args[0]);
        int k = Integer.parseInt(args[1]);

        Oblig3Precode precode = new Oblig3Precode(n);
        SieveOfEratosthenes soe = new SieveOfEratosthenes(n);
        int[] primes = soe.getPrimes();
        int[] factors = findFactors(n, primes);

        checkFactors(n, factors);

        for (int factor : factors) {
            precode.addFactor(n, factor);
        }

        precode.writeFactors();
    }


    public static int[] findFactors(int base, int[] primes) {
        ArrayList<Integer> factors = new ArrayList<>(); 
        int new_base = base;


        while (true) { 
            for (int prime : primes) {
                if (prime == new_base) {
                    factors.add(prime);
                    int[] arr = new int[factors.size()]; // Not sure if this is actually better then just using ArrayLists
                    int i = 0;
                    for (int factor : factors) {
                        arr[i++] = factor;
                    }
                    return arr;
                }
                if (new_base % prime == 0) {
                    factors.add(prime);
                    new_base /= prime;
                    break;
                }
            }
        }
    } 

    public static void checkFactors(int base, int[] factors) {
        int sum = 1;
        for (int factor : factors) {
            sum *= factor;
        }
        assert sum == base;
    }
}



