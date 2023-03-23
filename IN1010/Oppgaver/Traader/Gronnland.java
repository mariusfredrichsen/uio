public class Gronnland {
    public static void main(String[] args) {
        Bank bank = new Bank(1000);
        new Thread(new Raner(bank, 16)).start();   
        new Thread(new Person(10, bank)).start();
    }

    //banken blir monitoren som inneholder metodene
        //her lager man conditions for om den er tom eller om den er full
        //venter på at den andre har gjort noe
    //Person blir produsenten
    //Raner blir en raner?

}
