

public class TestResept {
    public static void main(String[] args) {
        Narkotisk narko = new Narkotisk("narko", 100, 100, 1000);
        Vanlig vanlig = new Vanlig("vanlig", 200, 2000);
        Vanedannende vanedannende = new Vanedannende("vanedannende", 300, 300, 3000);

        MilResept milresept = new MilResept(narko, new Lege("milly"), 50);
        PResept presept = new PResept(vanlig, new Lege("peppy"), 2, 100);
        BlåResept blåresept = new BlåResept(vanedannende, new Lege("blåppy"), 5, 3);

        System.out.println(milresept);
        System.out.println(presept);
        System.out.println(blåresept);

        milresept.bruk();
        milresept.bruk();
        System.out.println(milresept.bruk());
        System.out.println(milresept.bruk());

        System.out.println(milresept.prisAaBetale());
        System.out.println(presept.prisAaBetale());
        System.out.println(blåresept.prisAaBetale());
    }
}
