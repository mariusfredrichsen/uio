class Test {
    public static void main(String[] args) {
        dillDall(0);
    }

    public static void dillDall(int tall) {
        if (tall < 10) {
            System.out.println(tall);
            dillDall(++tall);
        }
    }
}