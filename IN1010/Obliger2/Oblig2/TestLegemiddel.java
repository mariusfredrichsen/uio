


public class TestLegemiddel {
    public static void main(String[] args) {
        Narkotisk narko = new Narkotisk("narko", 100, 100, 1000);
        Vanlig vanlig = new Vanlig("vanlig", 200, 2000);
        Vanedannende vanedannende = new Vanedannende("vanedannende", 300, 300, 3000);

        System.out.println("ID skal være 0 og id er " + narko.id);
        System.out.println("ID skal være 1 og id er " + vanlig.id);
        System.out.println("ID skal være 2 og id er " + vanedannende.id);
    }
}
