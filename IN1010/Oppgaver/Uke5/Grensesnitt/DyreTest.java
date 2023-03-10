package IN1010.Oppgaver.Uke5.Grensesnitt;

public class DyreTest {
    public static void main(String[] args) {
        Rovdyr[] rovdyr = new Rovdyr[2];
        Planteetere[] planteetere = new Planteetere[2];

        rovdyr[0] = new Bjorn();
        rovdyr[1] = new Ulv();

        planteetere[0] = new Elg();
        planteetere[1] = new Sau();

        rovdyr[0].jakt(planteetere[0]);
        planteetere[0].beskytt(rovdyr[0]);
        
        rovdyr[1].jakt(planteetere[1]);
        planteetere[1].beskytt(rovdyr[1]);
    }
}
