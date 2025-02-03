import java.util.Arrays;


public class A2 {
    

    public int[] sortArray(int[] a, int k) {

        int n = a.length;
        int[] biggest = new int[k];

        for (int i = 1; i < k; i++) {
            int j = i;
            while (j > 0 && a[j] > a[j-1]) {
                int tmp = a[j-1];
                a[j-1] = a[j];
                a[j] = tmp;
                j--;
            }
        }

        int i = k;
        int j = k-1;
        while (i < n) {
            if (a[i] > a[j]) {
                int tmp = a[j];
                a[j] = a[i];
                a[i] = tmp;

                while (j > 0 && a[j] > a[j-1]) {
                    tmp = a[j-1];
                    a[j-1] = a[j];
                    a[j] = tmp;
                    j--;
                }
                j = k-1;
            }
            i++;
        }

        for (int l = 0; l < k; l++) {
            biggest[l] = a[l];
        }

        return biggest;
    }
}
