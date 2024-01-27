import java.util.Scanner;


public class GameOfLife {
    public static void main(String[] args) {
        Verden verden = new Verden(20,20);
        verden.tegn();
        

        Scanner scan = new Scanner(System.in);
        while (scan.nextLine() == "") {
            verden.oppdatering();
            verden.tegn();
        }

    }
}
