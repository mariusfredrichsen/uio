import java.util.HashMap;
import java.io.File;
import java.util.Scanner;


public class Oblig2Del2 {
    

    public static void main(String[] args) {
        SubsekvensRegister register = new SubsekvensRegister();
        
        File fil;
        Scanner scan;

        try {
            fil = new File(args[0] + "/metadata.csv");
            scan = new Scanner(fil);
        } catch (Exception e) {
            throw new IllegalArgumentException("Kunne ikke lese filen");
        }   

        while (scan.hasNextLine()) {
            register.settInnSubsekvens(SubsekvensRegister.lesFil(args[0] + "/" + scan.nextLine().strip()));
        }

        while (register.antallSubsekvenser() != 1) {
            register.settInnSubsekvens(SubsekvensRegister.slaaSammen(register.taUtSubsekvens(), register.taUtSubsekvens()));
        }

        register.taUtSubsekvens().forEach((k, v) -> System.out.println(v));
        scan.close();

    }
}
