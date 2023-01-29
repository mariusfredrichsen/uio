package IN1010.Oppgaver;

class LikeStrenger{
    public static void main(String [] args){
        String navn1 = new String("Maria");
        String navn2 = new String("Maria");

        if(navn1.equals(navn2)){
            System.out.println("Navnene er like!");
        } else {
            System.out.println("Navnene er ikke like!");
        }
    }
}