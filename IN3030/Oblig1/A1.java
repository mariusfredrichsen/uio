import java.util.Arrays;
import java.util.Collections;


public class A1 {
    public int[] sortArray(int[] a, int k) {
        Arrays.sort(a);
        
        int[] biggest = new int[k];
        int j = 0;
        for (int i = a.length - 1; i >= a.length - k; i--) {
            biggest[j++] = a[i];
        }

        return biggest;
    }
}
