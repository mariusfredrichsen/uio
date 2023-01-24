class Person {
    Bil3 bil;
    String bilnummer;

    public Person(Bil3 Personbil) {
        bil = Personbil;
    }

    public void skriv_ut() {
        bilnummer = bil.hent_bilnummer();
        System.out.println("Bilnummeret på denne personens bil er: " + bilnummer);
    }


}