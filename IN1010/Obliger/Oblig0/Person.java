class Person {
    Object bil;
    String Bilnummer;

    public Person(Object Personbil) {
        bil = Personbil;
    }

    public void skriv_ut() {
        Bilnummer = bil.hent_bilnummer();
        System.out.println("Bilnummeret på denne personens bil er: " + Bilnummer);
    }


}