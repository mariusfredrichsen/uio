import java.util.Scanner;

public class TastaturDialog implements Dialog {
    Scanner scan;

    public TastaturDialog() {
        scan = new Scanner(System.in);
    }

    public boolean svarJaEllerNei(String sporsmal) {
        System.out.println(sporsmal);
        if (scan.nextLine().equals("j")) {
            return true;
        } else {
            return false;
        }
    }
}
