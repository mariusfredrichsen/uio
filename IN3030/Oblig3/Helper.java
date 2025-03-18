


public class Helper implements Runnable {
    SOEPar soe;
    int prime = -1;


    public Helper(SOEPar soe) {
        this.soe = soe;
    }

    @Override
    public void run() {
        while (true) {
            prime = soe.sieveHelper();
            System.out.println(prime);
            if (prime == -1) return;
            soe.traverse(prime);
        }
    }
}
