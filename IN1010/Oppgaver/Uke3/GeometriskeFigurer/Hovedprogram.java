import java.util.ArrayList;

class Hovedprogram {
    public static void main(String[] args) {
        ArrayList<Figur> figurer = new ArrayList<Figur>();

        figurer.add(new Firkant(3,7));
        figurer.add(new Sirkel(3));
        figurer.add(new Trekant(2,3));
        figurer.add(new Kvadrat(4,4));

        double total_area = 0;
        for (Figur figur : figurer) {
            total_area += figur.areal();
        }
        System.out.println(total_area);
    }    
}
