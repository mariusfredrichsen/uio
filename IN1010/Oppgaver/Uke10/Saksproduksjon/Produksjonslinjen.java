package IN1010.Oppgaver.Uke10.Saksproduksjon;

public class Produksjonslinjen {
    public static void main(String[] args) {
        KnivMonitor knivM = new KnivMonitor();
        SaksMonitor saksM = new SaksMonitor();

        Thread[] traadListe = new Thread[24];
        for (int i = 0; i < 12; i++) {
            KnivProdusent knivP = new KnivProdusent(knivM, 100);
            SaksProdusent saksP = new SaksProdusent(knivM, saksM);
            Thread t1 = new Thread(knivP);
            Thread t2 = new Thread(saksP);
            traadListe[i] = t1;
            traadListe[23-i] = t2;
            t1.start();
            t2.start();
        }
        for (Thread t : traadListe) {
            try {
                t.join();
            } catch (InterruptedException e) {
                System.out.println("Noe galt skjedde");
            }
        }

        saksM.skrivUtAntSakser();

    }
}
