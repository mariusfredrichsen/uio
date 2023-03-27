package IN1010.Oppgaver.Uke10.AvbruttTraad;

class AvbrytTraaden {
    public static void main(String[] args) {
        Thread t = new Thread(new MyRunnable());
        t.start();
        t.interrupt();
    }
}