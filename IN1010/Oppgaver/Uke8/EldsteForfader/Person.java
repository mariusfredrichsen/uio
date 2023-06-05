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

    public Person finnEldsteKjenteForfader(){
        if(mor == null){
          if(far == null)
            return this;
          else
            return far.finnEldsteKjenteForfader();
        } 
    
        if(far == null){
          return mor.finnEldsteKjenteForfader();
        }
    
        Person morside = mor.finnEldsteKjenteForfader();
        Person farside = far.finnEldsteKjenteForfader();
    
        if(morside.compareTo(farside) < 0)
          return morside;
        else
          return farside;
      }
}
