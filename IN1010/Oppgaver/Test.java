package IN1010.Oppgaver;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class Test {
    public static void main(String[] args) throws IOException {
        FileWriter fil = new FileWriter("fil.txt");
        fil.write("Linje 1\n");
        fil.write("Linje 3\n");
        fil.write("\n");
        fil.close();
    }
}
