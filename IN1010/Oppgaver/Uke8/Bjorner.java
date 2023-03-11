package IN1010.Oppgaver.Uke8;

public class Bjorner {
    static boolean teddy(int n) {
        System.out.println(n);
        if (n == 42) {
            return true;
        } else {
            if (n % 5 == 0) {
                return teddy(n-42);
            }
            if (n % 2 == 0) {
                return teddy(n/2);
            }
            if (n % 3 == 0 || n % 4 == 0) {
                return teddy((n%10)+((n%100)/10));
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // int n = 208;
        // System.out.println(250-42);
        System.out.println(teddy(250));
    }
}
