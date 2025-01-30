


public class Printer implements Runnable {
    private int ind;

    public Printer(int ind) {
        this.ind = ind;
    }

    @Override
    public void run() {
        System.out.println("Traad nr: " + ind + "sier hei");

        try {
            Thread.sleep(1000);
            System.out.println("Traad nr: " + ind + "sier hei etter å ha ventet et sekund");
        } catch (InterruptedException e) { return; }
    }
}
