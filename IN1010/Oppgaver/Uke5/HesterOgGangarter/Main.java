package IN1010.Oppgaver.Uke5.HesterOgGangarter;
public class Main {
    public static void main(String[] args) {
        EngelskFullblodshest EF = new EngelskFullblodshest("EF", 10);
        Islandshest IH = new Islandshest("Hilmir", 200);
        PasoFino PF = new PasoFino("Paso", 50);

        EF.galopp();
        EF.skritt();
        EF.trav();
        
        PF.galopp();
        PF.skritt();
        PF.trav();
        PF.toelt();

        IH.galopp();
        IH.skritt();
        IH.trav();
        IH.toelt();
        IH.pass();
    }
}
