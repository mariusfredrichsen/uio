package IN1010.Oppgaver.Uke10.AvbruttTraad;

class MyRunnable implements Runnable {
    public void run() {
        try {
                System.out.println(1);
                Thread.sleep(1000);
                System.out.println(2);
            }
            catch (InterruptedException exception) {
            System.out.println(3);
        }
        System.out.println(4);
    }
}