public abstract class Skinnegående {
    String id;
    int sporvidde;

    Skinnegående neste;
    Skinnegående forrige;

    public Skinnegående(String id, int sporvidde) {
        this.id = id;
        this.sporvidde = sporvidde;

        this.neste = null;
        this.forrige = null;
    }

    public String hentId() {
        return id;
    }

    public int hentSporvidde() {
        return sporvidde;
    }

    public void sjekkSporvidde() throws FeilSporvidde {
        if (this.neste != null) {
            if (this.hentSporvidde() != this.neste.hentSporvidde()) throw new FeilSporvidde(this.neste.hentId(), this.neste.hentSporvidde());
            neste.sjekkSporvidde();
        }
    }
}
