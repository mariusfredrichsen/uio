


public class Rektangel {

    double l;
    double b;
    
    public Rektangel (double l, double b) {
        this.l = l;
        this.b = b;
    }
  
    public void oekLengde (int okning) {
        if (l + okning < 1) {
            System.out.println("Lengden blir for liten");
        } else {
            l += okning;
        }
    }
  
    public void oekBredde (int okning) {
        if (b + okning < 1) {
            System.out.println("Bredden blir for liten");
        } else {
            b += okning;
        }
    }
  
    public double areal () {
        return l*b;
    }
  
    public double omkrets () {
        return l*2 + b*2;
    }
}