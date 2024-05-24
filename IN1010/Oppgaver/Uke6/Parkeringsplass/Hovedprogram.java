public class Hovedprogram {
    public static void main(String[] args) {
        Parkeringsplass<Bil> bilparkering = new Parkeringsplass<>();
        Parkeringsplass<Motorsykkel> sykkelparkering = new Parkeringsplass<>();

        bilparkering.parker(new Bil("ASD", 5));
        sykkelparkering.parker(new Motorsykkel("ASD", 10));



    }
}
