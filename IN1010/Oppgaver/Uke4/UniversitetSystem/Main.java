package IN1010.Oppgaver.Uke4.UniversitetSystem;

public class Main {
    public static void main(String[] args) {
        String[] kurs = new String[2];
        BachelorStudent bachelorstudent = new BachelorStudent("Mari", 40, "IFI", kurs);
        MasterStudent masterstudent = new MasterStudent("Simon", 22, "IFI", kurs);
        Professor professor = new Professor("Joshi", 37, 450, "IFI", "Design");
        
    }
}
