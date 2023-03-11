public class Hovedprogram {
    public static void main(String[] args) {
        //Legemidler her:
        Narkotisk narkotisk1 = new Narkotisk("nark1", 100, 2.5, 7);
        Vanedannende vanedannende = new Vanedannende("vane", 200, 1, 3);
        Vanlig vanlig = new Vanlig("vanl", 50, 20);
        Narkotisk narkotisk2 = new Narkotisk("nark2", 100, 2.5, 7);

        //Leger her:
        Lege lege = new Lege("En lege");
        Spesialist spesialist = new Spesialist("Enda en lege", "mammaLikerBamserMedLitenM");

        //Resepter her:
        BlaaResept blaaResept = new BlaaResept(narkotisk1, lege, 1, 30);
        HvitResept hvitResept = new HvitResept(vanedannende, lege, 2, 4);
        MilResept milResept = new MilResept(vanlig, spesialist, 3);
        PResept pResept = new PResept(narkotisk2, spesialist, 4, 9);

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
