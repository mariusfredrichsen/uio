abstract class Bil {
    final String bilnummer;
    final int pris;

    Bil neste = null;
    Bil forrige = null;

    public Bil(String bilnummer, int pris) {
        this.bilnummer = bilnummer;
        this.pris = pris;
    }


    Bil finnBilR(Dialog dialog, boolean kunElektrisk) {
        if (kunElektrisk && !(this instanceof Elektrisk)) {
            if (neste == null) return null;
            return neste.finnBilR(dialog, kunElektrisk);
        }
        System.out.println(this);
        if (dialog.svarJaEllerNei("Vil du leie denne bilen?")) return this;
        if (neste == null) return null;
        return neste.finnBilR(dialog, kunElektrisk);
    }




    
}
