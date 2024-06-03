class TestEnkelListe {
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