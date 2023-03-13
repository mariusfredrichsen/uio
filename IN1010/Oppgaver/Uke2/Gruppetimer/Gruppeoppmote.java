package IN1010.Oppgaver.Uke2.Gruppetimer;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class Gruppeoppmoete {
    private String[] navn = new String[14];
    private boolean[] oppmoete = new boolean[14];
    
    public void les_av_fil(String filnavn) throws Exception {
        Scanner scan = new Scanner(new File(filnavn));
        int teller = 0;

        while (teller < navn.length && scan.hasNextLine()) {
            navn[teller] = scan.nextLine();
            teller++;
        }
    }

    public boolean har_mott_opp(String navn) {
        for (int i = 0; i < 14; i++) {
            if (this.navn[i] == navn) {
                return oppmoete[i];
            }
        }
    }

    public void skriv_ut() {
        int teller = 0;
        for (String student : navn) {
            if (oppmoete[teller]) {
                System.out.println(student + " har mott opp.");
            } else {
                System.out.println(student + " har ikke mott opp.");
            }
        }
    }


    // Fyll inn med metodene nevnt over.
}