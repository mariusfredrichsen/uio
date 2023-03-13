package IN1010.Oppgaver.Uke5.EgneExceptions;
class TestBokhylle {

    public static void main(String[] args) throws DuplikatException, IkkeMerPlassException {

        Bokhylle bokhylle = new Bokhylle(3);
        System.out.println("Setter inn boeker:");

        String[] titler = {"De doedes tjern", "Doppler", "Doppler", "Misery", "Gone with the Wind"};

        for (int i = 0; i < titler.length; i++) {
            try {
                bokhylle.settInn(new Bok(titler[i]));
            } catch(Exception e){
                System.out.println(e);
            }
        }
        System.out.println("\nSkriver ut boeker:");
        bokhylle.skrivBoeker();
    }
}