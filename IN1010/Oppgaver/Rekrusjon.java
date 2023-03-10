package IN1010.Oppgaver;

public class Rekrusjon {

    public static void rekrusivUtrisk(int i) {
        if (i <= 10) {
            System.out.println(i++);
            rekrusivUtrisk(i);
        } else {
            return;
        }
    }
    public static void main(String[] args) {
        rekrusivUtrisk(1);
    }
}
