public class Prioritetskoe <E extends Comparable <E>> extends Lenkeliste <E>{
    @Override
    public void leggTil(E x) {
        super.leggTil(x);
        
        for (int i = 0; i < super.stoerrelse(); i++) {
            Node nesteLenke = hode;
            for (int j = 0; j < super.stoerrelse(); j++) {
                try {
                    Node tempNesteLenke = nesteLenke.neste; 
    
                    E data1 = nesteLenke.data;
                    E data2 = tempNesteLenke.data;
                    if (data1.compareTo(data2) > 0) {
                        nesteLenke.data = data2;
                        nesteLenke = nesteLenke.neste;
                        nesteLenke.data = data1;
                    } 
                    nesteLenke = nesteLenke.neste;
                } catch (NullPointerException e) {
                    ;
                }
            }
        }
    }
}