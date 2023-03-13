package IN1010.Oppgaver.Uke7.Butikk;

import java.util.Iterator;

public class Butikk implements Iterable<Person>{
    Person foerst = null;

    class PersonIterator implements Iterator<Person> { //Lager en klasse slik at man kan iterere igjennom en objekt type, her blir det person
        private Person segSelv; //Personen man itererer igjennom
        
        public PersonIterator() {
            segSelv = foerst; //hver gang man itererer så vil man alltids starte på den første
        }

        public boolean hasNext() {
            return segSelv != null; //så lenge Personen denne holder på ikke er et null objekt så har den en til
        }

        public Person next() {
            Person personHolder = segSelv;
            segSelv = segSelv.neste;
            return personHolder; //går til neste person i rekka
        }
    }

    public void entreButikk(Person p) {
        if (foerst == null) {
            foerst = p;
        } else {
            Person nestePerson = foerst;
            while (nestePerson.neste != null) {
                nestePerson = nestePerson.neste;
            }
            nestePerson.neste = p;
        }
    }

    public String kassa() {
        if (foerst == null) {
            return "Ingen flere kunder. Vi stenger butikken for i dag.";
        } else {
            Person personHolder = foerst;
            foerst = foerst.neste;
            return personHolder.navn + " kjøper " + personHolder.data;
        }
    }

    public Iterator<Person> iterator() {
        return new PersonIterator();
    }
}
