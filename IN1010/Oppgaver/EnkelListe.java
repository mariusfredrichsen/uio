package IN1010.Oppgaver;

class EnkelListe {
    private Node start;

    public void settInnForan(String nyttInnhold) {
        Node ny = new Node(nyttInnhold);
        ny.neste = start;
        start = ny;
    }

    public void skrivUt() {
        skrivUtBaklengs(start.neste);
    }

    private void skrivUtBaklengs(Node n) {
        Node nesteLenke = start;
        while (nesteLenke.neste != null) {
            if (nesteLenke.neste == n) {
                break;
            }
            nesteLenke = nesteLenke.neste;
        }
        System.out.println(nesteLenke.innhold);
        if (nesteLenke == start) {
            return;
        }
        skrivUtBaklengs(nesteLenke);
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