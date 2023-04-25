import java.util.concurrent.CountDownLatch;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class GameOfLife {
    static int rad = 5;
    static int kol = 5;
    static JButton oekRad, minkRad, oekKol, minkKol, ferdig;
    static JLabel antRad, antKol;
    public static void main(String[] args) {
        CountDownLatch ferdigVent = new CountDownLatch(1);
        JFrame vindu = new JFrame("Game of life");
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vindu.setMinimumSize(new Dimension(500,500));

        JPanel valgPanel = new JPanel(new GridBagLayout());
        GridBagConstraints oppsett = new GridBagConstraints();


        oppsett.gridx = 0;
        oppsett.gridy = 0;
        oekRad = new JButton("+");
        class OekRad implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                antRad.setText("Antall rader: " + ++rad);
            }
        }
        oekRad.addActionListener(new OekRad());
        valgPanel.add(oekRad, oppsett);

        oppsett.gridy = 1;
        antRad = new JLabel("Antall rader: " + rad);
        valgPanel.add(antRad, oppsett);

        oppsett.gridy = 2;
        minkRad = new JButton("-");
        class MinkRad implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rad != 5) antRad.setText("Antall rader: " + --rad);
            }
        }
        minkRad.addActionListener(new MinkRad());
        valgPanel.add(minkRad, oppsett);

        oppsett.gridx = 2;
        oppsett.gridy = 0;
        oekKol = new JButton("+");
        class OekKol implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                antKol.setText("Antall kolonner: " + ++kol);
            }
        }
        oekKol.addActionListener(new OekKol());
        valgPanel.add(oekKol, oppsett);

        oppsett.gridy = 1;
        antKol = new JLabel("Antall kolonner: " + kol);
        valgPanel.add(antKol, oppsett);

        oppsett.gridy = 2;
        minkKol = new JButton("-");
        class MinkKol implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (kol != 5) antKol.setText("Antall kolonner: " + --kol);
            }
        }
        minkKol.addActionListener(new MinkKol());
        valgPanel.add(minkKol, oppsett);

        oppsett.gridx = 1;
        oppsett.gridy = 3;
        ferdig = new JButton("Ferdig");
        class Ferdig implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                ferdigVent.countDown();
            }
        }
        ferdig.addActionListener(new Ferdig());
        valgPanel.add(ferdig, oppsett);

        vindu.add(valgPanel);
        vindu.setLocationRelativeTo(null);
        vindu.pack();
        vindu.setVisible(true);

        try {
            ferdigVent.await();
        } catch (InterruptedException e) {}
        System.out.println(rad + " , " + kol);
        vindu.dispose();
        Kontroll kontroll = new Kontroll(rad, kol);
    }
}