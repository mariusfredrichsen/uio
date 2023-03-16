import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Legesystem {
    Koe<Pasient> pasienter = new Koe<>();
    Koe<Legemiddel> legemidler = new Koe<>();
    Prioritetskoe<Lege> leger = new Prioritetskoe<>();
    Koe<Resept> resepter = new Koe<>();

    public void lesFil(File fil) throws FileNotFoundException {
        Scanner scan = new Scanner(fil);
        String linje;
        int pTeller = 0;
        while (scan.hasNextLine()) {
            linje = scan.nextLine();
            String[] linjeListe = linje.strip().split(",");

            if (linje.charAt(0) == '#') {
                pTeller++;
            } else {
                if (pTeller == 1) {
                    pasienter.leggTil(new Pasient(linjeListe[0], linjeListe[1]));
                }
                
                if (pTeller == 2) {
                    String navn = linjeListe[0];
                    int pris = Integer.parseInt(linjeListe[2]);
                    double virkestoff = Double.parseDouble(linjeListe[3]);
                    int styrke = 0;
                    try {
                        styrke = Integer.parseInt(linjeListe[4]);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        ;
                    }
                    
                    if (linjeListe[1].equals("narkotisk")) {
                        legemidler.leggTil(new Narkotisk(navn, pris, virkestoff, styrke));
                    } else if (linjeListe[1].equals("vanedannende")) {
                        legemidler.leggTil(new Vanedannende(navn, pris, virkestoff, styrke));
                    } else if (linjeListe[1].equals("vanlig")) {
                        legemidler.leggTil(new Vanlig(navn, pris, virkestoff));
                    }
                }
            
                if (pTeller == 3) {
                    if (linjeListe[1] == "0") {
                        leger.leggTil(new Lege(linjeListe[0]));
                    } else {
                        leger.leggTil(new Spesialist(linjeListe[0], linjeListe[1]));
                    }
                }

                if (pTeller == 4) {
                    Legemiddel mPeker = legemidler.hode.data;
                    for (Legemiddel middel : legemidler) {
                        if (middel.id == Integer.parseInt(linjeListe[0])) mPeker = middel;
                    }

                    Lege lPeker = leger.hode.data;
                    for (Lege lege : leger) {
                        if (lege.navn.equals(linjeListe[1])) lPeker = lege;
                    }
                    
                    Pasient pPeker = pasienter.hode.data;
                    for (Pasient pasient : pasienter) {
                        if (pasient.id == Integer.parseInt(linjeListe[2])) pPeker = pasient;
                    }
                    
                    if (linjeListe[3].equals("hvit")) {
                        resepter.leggTil(new HvitResept(mPeker, lPeker, pPeker, Integer.parseInt(linjeListe[4])));
                    } else if (linjeListe[3].equals("blaa")) {
                        resepter.leggTil(new BlaaResept(mPeker, lPeker, pPeker, Integer.parseInt(linjeListe[4])));
                    } else if (linjeListe[3].equals("militaer")) {
                        resepter.leggTil(new MilResept(mPeker, lPeker, pPeker));
                    } else if (linjeListe[3].equals("p")) {
                        resepter.leggTil(new PResept(mPeker, lPeker, pPeker, Integer.parseInt(linjeListe[4])));
                    }
                }
            }
        }
    }

    public void skrivUt() {
        System.out.println("Pasienter:\n");
        for (Pasient pasient : pasienter) {
            System.out.println(pasient);
        }

        System.out.println("Leger:\n");
        for (Lege lege : leger) {
            System.out.println(lege);
        }

        System.out.println("Legemidler:\n");
        for (Legemiddel legemiddel : legemidler) {
            System.out.println(legemiddel);
        }

        System.out.println("Resepter:\n");
        for (Resept resept : resepter) {
            System.out.println(resept);
        }
    }

    public void leggTilLege(String navn) {
        leger.leggTil(new Lege(navn));
    }

    public void leggTilLege(String navn, String kode) {
        leger.leggTil(new Spesialist(navn, kode));
    }

    public void leggTilPasient(String navn, String fodselsnummer) {
        pasienter.leggTil(new Pasient(navn, fodselsnummer));
    }

    public void leggTilLegemiddel(String type, String legeNavn, int pris, double virkestoff, int styrke) {
        if (type.equals("1")) {
            legemidler.leggTil(new Vanlig(legeNavn, pris, virkestoff));
        } else if (type.equals("2")) {
            legemidler.leggTil(new Vanedannende(legeNavn, pris, virkestoff, styrke));
        } else if (type.equals("3")) {
            legemidler.leggTil(new Narkotisk(legeNavn, pris, virkestoff, styrke));
        } else {
            System.out.println("Noe gikk galt.");
        }
    }

    public void leggTilResept(String legeNavn, String type, int legemiddelID, int pasientID, int reit) throws UlovligUtskrift {
        for (Lege lege : leger) {
            if (lege.navn.equals(legeNavn)) {
                for (Legemiddel legemiddel : legemidler) {
                    if (legemiddel.id == legemiddelID) {
                        for (Pasient pasient : pasienter) {
                            if (pasient.id == pasientID) {
                                if (type.equals("1")) {
                                    lege.skrivBlaaResept(legemiddel, pasient, reit);
                                    return;
                                } else if (type.equals("2")) {
                                    lege.skrivHvitResept(legemiddel, pasient, reit);
                                    return;
                                } else if (type.equals("3")) {
                                    lege.skrivMilResept(legemiddel, pasient);
                                    return;
                                } else if (type.equals("4")) {
                                    lege.skrivPResept(legemiddel, pasient, reit);
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
    }




    public static void main(String[] args) {

    }
}
