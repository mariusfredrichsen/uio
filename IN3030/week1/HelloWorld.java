


class HelloWorld {
    public static void main(String[] args) {
        
        int k = 10;

        for (int i = 0; i < k; i++) {
            Thread t = new Thread(new Printer(i));
            t.start();
        }
    }
}
