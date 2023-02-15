package IN1010.Obliger.Oblig2;

public class Hovedprogram {
    public static void main(String[] args) {
        //Legemidler her:
        Narkotisk narkotisk1 = new Narkotisk("nark1", 100, 2.5, 1, 7);
        Vanedannende vanedannende = new Vanedannende("vane", 200, 1, 2, 3);
        Vanlig vanlig = new Vanlig("vanl", 50, 20, 3);
        Narkotisk narkotisk2 = new Narkotisk("nark2", 100, 2.5, 4, 7);

        //Leger her:
        Lege lege = new Lege("En lege");
        Spesialist spesialist = new Spesialist("Enda en lege", "mammaLikerBamserMedLitenM");

        //Resepter her:
        BlaaResepter blaaResept = new BlaaResepter(1, narkotisk1, lege, 1, 30);
        HviteResepter hvitResept = new HviteResepter(2, vanedannende, lege, 2, 4);
        MilResept milResept = new MilResept(3, vanlig, spesialist, 3);
        PResept pResept = new PResept(4, narkotisk2, spesialist, 4, 9);

        System.out.println(narkotisk1);
        System.out.println(vanedannende);
        System.out.println(vanlig);
        System.out.println(narkotisk2);
        System.out.println();
        System.out.println(lege);
        System.out.println(spesialist);
        System.out.println();
        System.out.println(blaaResept);
        System.out.println(hvitResept);
        System.out.println(milResept);
        System.out.println(pResept);

    }
}
