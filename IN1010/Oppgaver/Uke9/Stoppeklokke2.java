import java.util.Scanner;

class Stoppeklokke2 {
    public static void main(String[] arg) {
	Scanner tastatur = new Scanner(System.in);
	System.out.print("Trykk Return for aa starte... ");
	tastatur.nextLine();

	Thread klokke = new Thread(new Klokke());
	klokke.start();

	System.out.print("Trykk Return for aa stoppe...");
	tastatur.nextLine();
	klokke.interrupt();
	System.out.println("Takk for naa");
    }
}