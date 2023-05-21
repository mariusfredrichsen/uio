class Gjest {
    String navn;
    Rom rom = null;

    Gjest(String navn) {
        this.navn = navn;
    }

    void settRom(Rom rom) {
        this.rom = rom;
    }
}
