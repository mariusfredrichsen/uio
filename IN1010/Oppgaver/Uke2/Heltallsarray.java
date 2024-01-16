


public class Heltallsarray {
    public static void main(String[] args) {
        int nul = 0;
        int en = 1;
        int to = 2;
        int tre = 3;
        int fire = 4;

        System.out.println(nul);
        System.out.println(en);
        System.out.println(to);
        System.out.println(tre);
        System.out.println(fire);

        int[] heltallsarray = new int[5];
        for (int i = 0; i < heltallsarray.length; i++) heltallsarray[i] = i;
        for (int tall : heltallsarray) System.out.println(tall);
    }
}
