


public class RektangelHovedprogram {
    public static void main(String[] args) {
        Rektangel r1 = new Rektangel(2, 4);
        Rektangel r2 = new Rektangel(3,6);

        System.out.println("Rektangel1 areal: " + r1.areal() + "\nRektangel2 areal: " + r2.areal());
        
        r1.oekBredde(3);
        r2.oekLengde(4);

        System.out.println("Rektangel1 omkrets: " + r1.omkrets() + "\nRektangel2 omkrets: " + r2.omkrets());
    }
}