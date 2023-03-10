package IN1010.Oppgaver.Uke6.GeometriskeFigurerOgBeholder;

public class Beholder <E> {
    private E figur;

    public void settInn(E figur) {
        this.figur = figur;
    }

    public E taUt() {
        return figur;
    }


}
