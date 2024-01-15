import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;


public class Emne {
    private String emnekode;
    private HashMap<String, Student> studenter;
    private Retter[] rettere;
    private ArrayList<Oblig> obliger;

    public Emne(String emnekode, HashMap<String, Student> studenter, Retter[] rettere) {
        this.emnekode = emnekode;
        this.studenter = studenter;
        this.rettere = rettere;
        obliger = new ArrayList<Oblig>();
    }

    public void administrer() {
        Scanner scan = new Scanner(System.in);
        
        System.out.println(emnekode + ":");
        System.out.println("O: Ny oblig\n" + "F: Frist ute, start retting\n" + "L: Lag eksamensliste\n" + "A: Avslutt");

        String input;
        while (true) {
            input = scan.nextLine().toLowerCase();

            if (input.equals("O")) {
                _opprettOblig();
                scan.close();
                break;
            } else if (input.equals("F")) {
                _startRetting();
                scan.close();
                break;
            } else if (input.equals("L")) {
                _skrivEksamensListe();
                scan.close();
                break;
            }
        }
    }

    private void _opprettOblig() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Oppgi fristen på formen ååmmdd:\n> ");
        obliger.add(new Oblig(scan.nextLine()));
        scan.close();
    }

    private void _startRetting() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Oppgi dagens dato:\n> ");
        String dato = scan.nextLine();

        for (Oblig oblig : obliger) {
            if (oblig.klarForRetting(dato)) {
                oblig.fordelRetting(oblig.hentBesvarelser(), rettere);
            }
        }   
    }

    private void _skrivEksamensListe() {
        System.out.println("Studenter som har bestått:");
        for (String student : studenter.keySet()) {
            if (studenter.get(student).altGodkjent(obliger.size())) {
                System.out.println(studenter.get(student).hentBrukernavn());
            }
        }
    }

}