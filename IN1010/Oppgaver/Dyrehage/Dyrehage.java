public class Dyrehage {
    private Dyr[] dyrebur;

    public Dyrehage(int stoerrelse) {
        dyrebur = new Dyr[stoerrelse];
    }

    public void settInnDyr(Dyr dyr) {
        for (int i=0; i<dyrebur.length; i++){
            if (dyrebur[i] == null){
                dyrebur[i] = dyr;
                System.out.println("Satt inn et dyr :)");
                return;
            }
        }
        System.out.println("dyreburet er fullt!");     
    }

    public void lagLyd() {
        for (int i = 0; i < dyrebur.length; i++) {
            if (dyrebur[i] != null){
                dyrebur[i].lagLyd();
            } 
        }
    }
}

