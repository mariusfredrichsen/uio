package IN1010.Oppgaver.Uke8;

public class GCD {
    public static int gcd(int a, int b) {
        if (a < b) {
            int tmp = a;
            a = b;
            b = tmp;
        }
        int c = a % b;
        if (c != 0) {
            return gcd(b, c);
        }
        return b;
    }

    public static int gcdIterativ(int a, int b) {
        int c = -1;
        while (c != 0) {
            if (a < b) {
                int tmp = a;
                a = b;
                b = tmp;
            }
            c = a % b;
            a = c;
        }
        return b;
    }
    
    public static void main(String[] args) {
        System.out.println(gcd(20, 12));
        System.out.println(gcdIterativ(20, 12));
    }
}