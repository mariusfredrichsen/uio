
public class Helper implements Runnable {

    SOEPar soe;
    int start, end;
    int[] primes;

    public Helper(SOEPar soe, int start, int end, int[] primes) {
        this.soe = soe;
        this.start = start;
        this.end = end;
        this.primes = primes;
    }

    @Override
    public void run() {
        for (int i = start; i < end; i++) {
            soe.traverse(primes[i]);
            System.out.println(i);
        }
    }
}
