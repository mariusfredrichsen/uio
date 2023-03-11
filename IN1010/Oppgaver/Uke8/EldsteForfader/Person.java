class Person implements Comparable<Person>{
    String navn;
    int fodselsar;
    Person mor = null;
    Person far = null;

    public Person(int fodselsar, String navn, Person mor, Person far) {
        this.navn = navn;
        this.fodselsar = fodselsar;
        this.mor = mor;
        this.far = far;
    }

    public String toString() {
        return "Navn: " + navn + " Fodselsar: " + fodselsar + " Mor: " + mor + " Far: " + far;
    }

    public int compareTo(Person p) {
        return fodselsar - p.fodselsar;
    }

    public Person finnEldsteKjenteForfader() {
        if (mor == null && far == null) {
            return this;
        } else if (mor != null) {
            return mor.finnEldsteKjenteForfader();
        } else if (far != null) {
            return far.finnEldsteKjenteForfader();
        } 

        Person mamma = mor.finnEldsteKjenteForfader();
        Person pappa = far.finnEldsteKjenteForfader();
        
        if (mamma.compareTo(pappa) > 0) {
            return far;
        }
        return mor;
    }
}
