public class Hovedprogram {
    public static void main(String[] args) {
        Butikk butikken = new Butikk();
        
        butikken.entreButikk(new Person("Stig", "Sitronsaft"));
        butikken.entreButikk(new Person("Hedda", "Engangskopper"));
        butikken.entreButikk(new Person("Jawad", "Pasta"));
        butikken.entreButikk(new Person("Henrik", "Kaffe"));
        butikken.entreButikk(new Person("Mathias", "Tomatsuppe"));

        System.out.println("BUTIKK-KØ: ");
        for(Person p : butikken){
          System.out.println(p);
        }
    }
}
