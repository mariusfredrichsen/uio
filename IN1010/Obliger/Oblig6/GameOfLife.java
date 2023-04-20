import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.CountDownLatch;

import javax.swing.*;


public class GameOfLife {
    public static void main(String[] args) throws InterruptedException {
        JFrame vindu = new JFrame("Game Of Life");
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Lager panelet for å velge antRad og antKol
        int[] antTeller = {0,0};
        JButton[] knapper = new JButton[4]; //0 for pluss rad, 1 for - rad, 2 for pluss kol, 3 for minus kol
        JLabel[] tekster = new JLabel[2]; //0 for rad og 1 for kol
        JButton ferdigKnapp = null;
        int knappTeller = 0;

        JPanel valgPanel = new JPanel(new GridLayout(4,3));
        for (int rad = 0; rad < 4; rad++) {
            for (int kol = 0; kol < 3; kol++) {
                if (kol == 1) {
                    if (rad == 2) {
                        ferdigKnapp = new JButton("Ferdig");
                        ferdigKnapp.setFont(new Font("Comic Sans MS", Font.BOLD, 32));
                        ferdigKnapp.setPreferredSize(new Dimension(150,150));
                        valgPanel.add(ferdigKnapp);
                    } else {
                        JLabel filler = new JLabel("");
                        valgPanel.add(filler);
                    }
                } else {
                    if (rad == 0) {
                        JLabel topTekst = new JLabel("Antall rader");
                        if (kol == 2) topTekst.setText("Antall kolonner");
                        topTekst.setHorizontalAlignment(JLabel.CENTER);
                        if (kol == 1) topTekst.setText("Antall kolonner");
                        topTekst.setFont(new Font("Comic Sans MS", Font.BOLD, 32));
                        topTekst.setBorder(BorderFactory.createLineBorder(new Color(128, 128, 128)));
                        valgPanel.add(topTekst);
                    } else if (rad == 2) {
                        JLabel antTekst = new JLabel("");
                        if (kol == 0) antTekst.setText("0");
                        else antTekst.setText("0");
                        antTekst.setFont(new Font("Comic Sans MS", Font.BOLD, 32));
                        antTekst.setHorizontalAlignment(JLabel.CENTER);
                        antTekst.setVerticalAlignment(JLabel.CENTER);
                        antTekst.setOpaque(true);
                        antTekst.setBackground(new Color(153, 204, 255));
                        antTekst.setBorder(BorderFactory.createLineBorder(new Color(128, 128, 128)));
                        if (kol == 2) tekster[kol-1] = antTekst;
                        else tekster[kol] = antTekst;
                        valgPanel.add(antTekst);
                    } else {
                        JButton knapp = new JButton("");
                        if (rad == 1) knapp.setText("+");
                        else knapp.setText("-");
                        knapp.setFont(new Font("Comic Sans MS", Font.BOLD, 64));
                        knapp.setPreferredSize(new Dimension(150,150));
                        knapper[knappTeller++] = knapp;
                        valgPanel.add(knapp);
                    }
                }
            }
        }

        class oekRad implements ActionListener {
            int[] antTeller;

            oekRad(int[] antTeller) {
                this.antTeller = antTeller;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                tekster[0].setText(++antTeller[0] + "");
            }
        }
        knapper[0].addActionListener(new oekRad(antTeller));

        class oekKol implements ActionListener {
            int[] antTeller;

            oekKol(int[] antTeller) {
                this.antTeller = antTeller;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                tekster[1].setText(++antTeller[1] + "");
            }
        }
        knapper[1].addActionListener(new oekKol(antTeller));

        class minkeRad implements ActionListener {
            int[] antTeller;

            minkeRad(int[] antTeller) {
                this.antTeller = antTeller;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                if (antTeller[0] != 0) tekster[0].setText(--antTeller[0] + "");
            }
        }
        knapper[2].addActionListener(new minkeRad(antTeller));

        class minkeKol implements ActionListener {
            int[] antTeller;

            minkeKol(int[] antTeller) {
                this.antTeller = antTeller;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                if (antTeller[1] != 0) tekster[1].setText(--antTeller[1] + "");
            }
        }
        knapper[3].addActionListener(new minkeKol(antTeller));

        int[] avEllerPaa = {0};
        Verden[] verden = {null};
        CountDownLatch venterPaaTall = new CountDownLatch(1);
        JButton[][][] celleKnapper = {null};
        JLabel antLevende = new JLabel("Antall levende: 0");

        class ferdig implements ActionListener {
            Verden[] verden;
            int[] avEllerPaa;
            CountDownLatch venterPaaTall;
            JButton[][][] celleKnapper;

            ferdig(Verden[] verden, int[] avEllerPaa, CountDownLatch venterPaaTall, JButton[][][] celleKnapper) {
                this.verden = verden;
                this.avEllerPaa = avEllerPaa;
                this.venterPaaTall = venterPaaTall;
                this.celleKnapper = celleKnapper;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                if (antTeller[0] != 0 && antTeller[1] != 0) {
                    vindu.remove(valgPanel);
                    verden[0] = new Verden(antTeller[0], antTeller[1]); //lager verden
                    Celle[][] celler = verden[0].hentCeller();

                    JPanel hovedPanel = new JPanel(new GridBagLayout());
                    GridBagConstraints oppsett = new GridBagConstraints();
                    
                    oppsett.gridy = 0;
                    oppsett.gridx = 0;
                    hovedPanel.add(antLevende, oppsett);
                    
                    JButton start = new JButton("Start");
                    class startet implements ActionListener {
                        int[] avEllerPaa;
                        CountDownLatch venterPaaTall;
                        
                        startet(int[] avEllerPaa, CountDownLatch venterPaaTall) {
                            this.avEllerPaa = avEllerPaa;
                            this.venterPaaTall = venterPaaTall;
                        }

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            avEllerPaa[0] = 1;
                            venterPaaTall.countDown();
                        }
                    }
                    start.addActionListener(new startet(avEllerPaa, venterPaaTall));
                    oppsett.gridy = 0;
                    oppsett.gridx = 1;
                    hovedPanel.add(start, oppsett);

                    JButton avslutt = new JButton("Avslutt");
                    class avsluttet implements ActionListener {
                        int[] avEllerPaa;

                        avsluttet(int[] avEllerPaa) {
                            this.avEllerPaa = avEllerPaa;
                        }

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            avEllerPaa[0] = 2;
                        }
                    }
                    avslutt.addActionListener(new avsluttet(avEllerPaa));
                    oppsett.gridy = 0;
                    oppsett.gridx = 2;
                    hovedPanel.add(avslutt, oppsett);

                    celleKnapper[0] = new JButton[antTeller[0]][antTeller[1]];

                    class trykket implements ActionListener {
                        boolean lever = false;
                        int[] pos;
            
                        trykket(int[] pos) {
                            this.pos = pos;
                        }
            
                        public void actionPerformed(ActionEvent e) {
                            lever = !lever;
                            if (lever) {
                                celleKnapper[0][pos[0]][pos[1]].setBackground(Color.BLACK);
                                celler[pos[0]][pos[1]].settLevende();
                            } else {
                                celleKnapper[0][pos[0]][pos[1]].setBackground(Color.WHITE);
                                celler[pos[0]][pos[1]].settDoed();
                            }
                            antLevende.setText("Antall levende: " + verden[0].rutenett.antallLevende());
                        }
                    }

                    JPanel rutenett = new JPanel(new GridLayout(antTeller[0],antTeller[1]));
                    for (int rad = 0; rad < antTeller[0]; rad++) {
                        for (int kol = 0; kol < antTeller[1]; kol++) {
                            JButton celle = new JButton("");
                            int[] pos = {rad,kol};
                            celle.addActionListener(new trykket(pos));
                            celle.setOpaque(true);
                            celle.setBackground(Color.WHITE);
                            celle.setSize(new Dimension(50,50));
                            celleKnapper[0][rad][kol] = celle;
                            rutenett.add(celle);
                        }
                    }
                    oppsett.gridx = 0;
                    oppsett.gridwidth = 3;
                    oppsett.gridy = 1;
                    hovedPanel.add(rutenett, oppsett);

                    vindu.add(hovedPanel);
                    vindu.pack();
                    vindu.setLocationRelativeTo(null);
                }
            }
        }
        ferdigKnapp.addActionListener(new ferdig(verden, avEllerPaa, venterPaaTall, celleKnapper));

        vindu.add(valgPanel);

        vindu.pack();
        vindu.setLocationRelativeTo(null);
        vindu.setVisible(true);

        try {
            venterPaaTall.await();
        } catch (InterruptedException a) {}

        while (avEllerPaa[0] != 2) {
            if (avEllerPaa[0] == 1) {
                verden[0].oppdatering();
                Celle[][] celler = verden[0].hentCeller();
                for (int rad = 0; rad < celler.length; rad++) {
                    for (int kol = 0; kol < celler[rad].length; kol++) {
                        if (celler[rad][kol].erLevende()) {
                            celleKnapper[0][rad][kol].setBackground(Color.BLACK);
                        } else {
                            celleKnapper[0][rad][kol].setBackground(Color.WHITE);
                        }
                    }
                }
                antLevende.setText("Antall levende: " + verden[0].rutenett.antallLevende());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException a) {}
            }
        }
        if (avEllerPaa[0] == 2) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
            vindu.dispatchEvent(new WindowEvent(vindu, WindowEvent.WINDOW_CLOSING));
        }
    }
}
