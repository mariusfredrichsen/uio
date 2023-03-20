import java.util.Scanner;

class Stoppeklokke {
    public static void main (String[] arg) {
	Scanner tastatur = new Scanner(System.in);
	System.out.print("Trykk Return for aa starte... ");
	tastatur.nextLine();

	Thread klokke = new Thread(new Klokke());
	klokke.start();

	System.out.print("Trykk Return for aa stoppe...");
	tastatur.nextLine();
	System.out.println("Takk for naa");
    }
}

class Klokke implements Runnable {
    @Override
    public void run () {
	int tid = 0;
	while (true) {
	    System.out.print(tid + " ");
	    try {
		Thread.sleep(1000);
	    } catch (InterruptedException e) { return; }
	    ++tid;
	}
    }
}