public class Test {
    public static void main(String[] args) {
        String faenskap = "gulrot  1";

        String[] dritt = faenskap.strip().split(" ");
        if (dritt[1].equals("")) System.out.println(dritt[2]);
        else System.out.println(dritt[1]);

        int[] kuk = {1,2,3,4,5,6};

    }
}
