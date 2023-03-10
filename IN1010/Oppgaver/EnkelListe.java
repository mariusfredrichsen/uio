package IN1010.Oppgaver;

class EnkelListe {
    private Node start;

    public void settInnForan(String nyttInnhold) {
        Node ny = new Node(nyttInnhold);
        ny.neste = start;
        start = ny;
    }

    public void skrivUt() {
        skrivUtBaklengs(start);
    }

    private void skrivUtBaklengs(Node n) {
    }

    private class Node {
        private String innhold;
        private Node neste;

        Node(String s) {
            innhold = s;
        }
    }

    public static void main(String[] args) {

        EnkelListe eliste = new EnkelListe();
        eliste.settInnForan("Hei");
        eliste.settInnForan("paa");
        eliste.settInnForan("deg");
        eliste.settInnForan("din");
        eliste.settInnForan("gamle");
        eliste.settInnForan("sei!");

        eliste.skrivUt();
    }
}