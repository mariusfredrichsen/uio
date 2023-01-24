package IN1010.Oppgaver;

public class Heltallsarray {
    public static void main(String[] args) {
        int[] heltallarray = new int[5];

        for (int i = 0; i < heltallarray.length; i++) {
            heltallarray[i] = i;
        }

        for (int elem : heltallarray) {
            System.out.println(elem);
        }
    }
}
