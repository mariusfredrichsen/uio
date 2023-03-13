package IN1010.Oppgaver.Uke4.BilerOgBusser;

abstract public class Kjoretoy {
    String registernummer;
    String fabrikkmerke;
    String eier;
    public Kjoretoy(String registernummer, String fabrikkmerke, String eier) {
        this.registernummer = registernummer;
        this.fabrikkmerke = fabrikkmerke;
        this.eier = eier;
    }
    
    public void skrivUt() {
        System.out.println("registernummer: " + registernummer + "\nfabrikkmerke: " + fabrikkmerke + "\neier: " + eier);
    }
}
