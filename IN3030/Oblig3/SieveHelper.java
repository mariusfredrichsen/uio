
public class SieveHelper implements Runnable {

    SOEPar soe;
    int start, end;
    int[] primes;
    int numOfPrimes = 0; // counts one too many times

    public SieveHelper(SOEPar soe, int[] primes, int start, int end) {
        this.soe = soe;
        this.start = start;
        this.end = end;
        this.primes = primes;
    }

    @Override
    public void run() {
        for (int prime : primes) {
            soe.traverse(prime, start, end);
        }
    }
}
