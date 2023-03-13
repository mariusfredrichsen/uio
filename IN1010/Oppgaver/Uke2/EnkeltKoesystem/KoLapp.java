package IN1010.Oppgaver.Uke2.EnkeltKoesystem;

public class KoLapp{
    int ko_nummer;
    //Konstruktor
    public KoLapp(int nummer){
        ko_nummer = nummer;
    }

    //Returnerer et tildelt nummer paa kolappen.    
    public int hentNummer(){
        return ko_nummer;
    }
}