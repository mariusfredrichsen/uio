public class Labyrint {
    Rute[][] ruter;

    Labyrint(int rad, int kol) {
        ruter = new Rute[rad][kol];
    }


    public String toString() {
        for (int rad = 0; rad < ruter.length; rad++) {
            for (int kol = 0; kol < ruter[rad].length; kol++) {
                if (ruter[rad][kol] instanceof SortRute) {
                    System.out.print("# ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }
}