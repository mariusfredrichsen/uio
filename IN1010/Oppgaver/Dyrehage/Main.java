public class Main {
    public static void main(String[] args) {
        Dyrehage dyrehage = new Dyrehage(4);
        Loeve tryone = new Loeve();
        Loeve simba = new Loeve();
        Fisk nemo = new Fisk();
        Loeve knut = new Loeve();
        Loeve albert = new Loeve();

        dyrehage.settInnDyr(tryone);
        dyrehage.settInnDyr(simba);
        dyrehage.settInnDyr(knut);
        dyrehage.settInnDyr(nemo);
        dyrehage.settInnDyr(albert);

        dyrehage.lagLyd();
    }
}

