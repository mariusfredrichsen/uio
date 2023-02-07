package IN1010.Oppgaver.ArvAvEgenskaper;

public class Hovedprogram {
    public static void main(String[] args) {
        B b1 = new B();
        B b2 = b1;
        A a1 = b1;

        b2.skrivUt();
        a1.skrivUt();
    }
}
