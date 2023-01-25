package IN1010.Oppgaver.TemperaturLeser;
import java.util.Scanner;
import java.io.File;

class TemperaturLeser {
    public static void main(String[] args) throws Exception {
        Scanner fil = new Scanner(new File("temperatur.txt"));
        String[] temperaturer = new String[12];
        int teller = 0;

        while(fil.hasNextLine()) {
            temperaturer[teller] = fil.nextLine();
            teller++;
        }

        for(int i = 0; i < temperaturer.length; i++) {
            System.out.println(temperaturer[i]);
        }
    }
}