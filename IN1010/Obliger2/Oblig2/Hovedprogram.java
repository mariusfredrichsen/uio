public class Hovedprogram {
    public static void main(String[] args) {

        Lege lege = new Lege("Marius");
        Spesialist spesialist = new Spesialist("Sofie", "ASDASD");

        Narkotisk narko = new Narkotisk("narko", 100, 100, 100);
        Vanlig vanlig = new Vanlig("vanlig", 200, 200);
        Vanedannende vanedannende = new Vanedannende("vanedannende", 300, 300, 300);

        MilResept milresept = new MilResept(narko, lege, 50);
        PResept presept = new PResept(vanlig, spesialist, 2, 100);
        BlåResept blåresept = new BlåResept(vanedannende, lege, 5, 3);

        System.out.println(lege);
        System.out.println(spesialist);

        System.out.println(narko);
        System.out.println(vanlig);
        System.out.println(vanedannende);

        System.out.println(milresept);
        System.out.println(presept);
        System.out.println(blåresept);



    }
}
