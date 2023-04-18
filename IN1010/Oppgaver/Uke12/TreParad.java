import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

import javax.swing.*;

public class TreParad {
    public static boolean vinner(JButton[][] rutenett) {
        //sjekker for radene
        for (int i = 0; i < 3; i++) {
            if (rutenett[i][0].getText().equals(rutenett[i][1].getText()) && rutenett[i][0].getText().equals(rutenett[i][2].getText()) && !rutenett[i][0].getText().equals("")) {
                rutenett[i][0].setBackground(Color.RED);
                rutenett[i][1].setBackground(Color.RED);
                rutenett[i][2].setBackground(Color.RED);
                return true;
            }
        }

        //sjekker for kolonnene
        for (int i = 0; i < 3; i++) {
            if (rutenett[0][i].getText().equals(rutenett[1][i].getText()) && rutenett[0][i].getText().equals(rutenett[2][i].getText()) && !rutenett[0][i].getText().equals("")) {
                rutenett[0][i].setBackground(Color.RED);
                rutenett[1][i].setBackground(Color.RED);
                rutenett[2][i].setBackground(Color.RED);
                return true;
            }
        }

        //sjekker for diagonalene
        if (rutenett[0][0].getText().equals(rutenett[1][1].getText()) && rutenett[0][0].getText().equals(rutenett[2][2].getText()) && !rutenett[0][0].getText().equals("")) {
            rutenett[0][0].setBackground(Color.RED);
            rutenett[1][1].setBackground(Color.RED);
            rutenett[2][2].setBackground(Color.RED);
            return true;
        }

        if (rutenett[0][2].getText().equals(rutenett[1][1].getText()) && rutenett[0][2].getText().equals(rutenett[2][0].getText()) && !rutenett[0][2].getText().equals("")) {
            rutenett[0][2].setBackground(Color.RED);
            rutenett[1][1].setBackground(Color.RED);
            rutenett[2][0].setBackground(Color.RED);
            return true;
        }
        
        //ingen vinner
        return false;
    }

    public static void main(String[] args) {
        JFrame vindu = new JFrame();
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel rutenett = new JPanel(new GridLayout(3,3));
        JButton[][] ruter = new JButton[3][3];
        int[] teller = {0};

        class Trykket implements ActionListener {
            int[] radKol;
            boolean trykket = false;
            int[] teller;

            Trykket(int[] radKol, int[] teller) {
                this.radKol = radKol;
                this.teller = teller;
            }

            public void actionPerformed(ActionEvent ae) {
                if (!trykket) {
                    if (teller[0]++ % 2 == 0) {
                        ruter[radKol[0]][radKol[1]].setText("X");
                    } else {
                        ruter[radKol[0]][radKol[1]].setText("O");
                    }
                    trykket = true;
                }
            }
        }

        for (int rad = 0; rad < 3; rad++) {
            for (int kol = 0; kol < 3; kol++) {
                JButton knapp = new JButton();
                ruter[rad][kol] = knapp;
                knapp.setPreferredSize(new Dimension(125,125));
                knapp.setFont(new Font("Comic Sans MS", Font.BOLD, 32));
                int[] tmpRadKol = {rad,kol};
                knapp.addActionListener(new Trykket(tmpRadKol, teller)); 
                knapp.setBackground(Color.WHITE);
                knapp.setOpaque(true);
                rutenett.add(knapp);
            }
        }

        vindu.add(rutenett);
        vindu.pack();
        vindu.setLocationRelativeTo(null);
        vindu.setVisible(true);

        while (!vinner(ruter) && teller[0] != 10) {
            if (teller[0] == 9) {
                for (int rad = 0; rad < 3; rad++) {
                    for (int kol = 0; kol < 3; kol++) {
                        ruter[rad][kol].setBackground(new Color(128, 128, 128));
                    }
                }
                teller[0]++;
            }
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {}
        vindu.dispatchEvent(new WindowEvent(vindu, WindowEvent.WINDOW_CLOSING));
    }
}
