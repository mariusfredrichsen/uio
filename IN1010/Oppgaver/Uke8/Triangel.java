package IN1010.Oppgaver.Uke8;

public class Triangel {
    static void triangel(int m, int n) {
        for (int i = 0; i < m; i++) {
            System.out.print("*");
        }
        System.out.println();
        if (m == n) {
            return;
        } else {
            triangel(m+1, n);
        }
        for (int i = 0; i < m; i++) {
            System.out.print("*");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        triangel(1, 10);
    }
}
