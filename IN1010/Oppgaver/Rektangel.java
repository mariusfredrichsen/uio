package IN1010.Oppgaver;

class Rektangel {
    double lengde;
    double bredde;

    public Rektangel (double l, double b) {   // Konstruktør
        lengde = l;
        bredde = b;
    }
  
    public void oekLengde (int okning) {    // Oek lengden som angitt
        lengde += okning;
    }
  
    public void oekBredde (int okning) {    // Oek bredden som angitt
        bredde += okning;
    }
  
    public double areal () {     // Beregn mitt areal
        return bredde*lengde;
    }
  
    public double omkrets () {   // Beregn min omkrets
        return bredde*2+lengde*2;
    }
  }

class RektangelHovedprogram {
    public static void main(String[] args) {
        Rektangel en = new Rektangel(4, 6);
        Rektangel to = new Rektangel(7, 6);

        System.out.println(en.areal());
        System.out.println(to.areal());

        en.oekLengde(4);
        to.oekBredde(6);

        System.out.println(en.omkrets());
        System.out.println(to.omkrets());
    }
}