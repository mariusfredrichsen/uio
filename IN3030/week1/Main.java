import java.util.Random;


public class Main {
    public static void main(String[] args) {
        
        int[] a = this.createArray(100);

        FindMax fm = new FindMax(a);


    }

    static public int[] createArray(int n) {
        int[] a = new int[n];
        Random rand = new Random();

        long start = System.nanoTime(); 
        for (int i = 0; i < n; i++) {
            int newNum = rand.nextInt();
            
            a[i] = newNum;
        }
        long end = System.nanoTime();
        System.out.println("Time spent creating array with length " + n + " : " + ((end - start) / 1000000.0));
        
        return a;
    }
}
