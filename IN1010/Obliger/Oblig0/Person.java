class Person {
    Bil3 bil;

    public Person(Bil3 Personbil) {
        bil = Personbil;
    }

    public void skriv_ut() {
        System.out.println("Bilnummer: " + bil.hent_bilnummer());
    }


}