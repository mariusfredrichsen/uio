package IN1010.Oppgaver.Uke1.Baathus;

public class TestBaathus {
    public static void main(String[] args) {
        Baathus baathus1 = new Baathus(3);
        Baat baat1 = new Baat("a");
        Baat baat2 = new Baat("ab");
        Baat baat3 = new Baat("abc");
        Baat baat4 = new Baat("abcd");
        baathus1.settInn(baat1);
        baathus1.settInn(baat2);
        baathus1.settInn(baat3);
        baathus1.settInn(baat4);
        baathus1.SkrivBaater();
    }
}
