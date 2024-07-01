public class CDAlbum implements Comparable<CDAlbum> {
    public String artist;
    String tittel;
    String utgivelsesår;

    public CDAlbum(String artist, String tittel, String utgivelsesår) {
        this.artist = artist;
        this.tittel = tittel;
        this.utgivelsesår = utgivelsesår;
    }

    @Override
    public int compareTo(CDAlbum album) {
        return this.artist.compareTo(album.artist);
    }

    @Override
    public String toString() {
        return this.artist;
    }
}
