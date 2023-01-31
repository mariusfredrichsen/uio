package IN1010.Oppgaver.Studentsystem;

import java.io.FileNotFoundException;

class Hovedprogram {
    public static void main(String[] args) throws FileNotFoundException {
        Studentsystem system = new Studentsystem();
        system.lesFil("emnestudenter.txt");
    }
}
