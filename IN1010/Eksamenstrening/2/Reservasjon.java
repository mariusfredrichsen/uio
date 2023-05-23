class Reservasjon {
    Gjest gjest;
    final int onsketSengeplass;
    boolean kjokken;

    Reservasjon forrigeR = null;
    Reservasjon nesteR = null; 

    Reservasjon(Gjest gjest, int onsketSengeplass, boolean kjokken) {
        this.gjest = gjest;
        this.onsketSengeplass = onsketSengeplass;
        this.kjokken = kjokken;
    }
}
