package IN1010.Oppgaver.Uke10.BankOgSaldo;

public class Norge {
    public static void main(String[] args) {
        Bank bank = new Bank(1000);
        DaarligMedborger albert = new DaarligMedborger(bank, 40, 200);
        GodMedborger hansen = new GodMedborger(bank, 20, 200);
        Thread t1 = new Thread(albert);
        Thread t2 = new Thread(hansen);
        t1.start();
        t2.start();
    }
}
