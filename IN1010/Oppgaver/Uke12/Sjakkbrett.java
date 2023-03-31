import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.*;

public class Sjakkbrett {
    public static void main(String[] args) {
        JFrame vindu = new JFrame("Vindu");
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        vindu.add(panel);
        
        JPanel rutenett = new JPanel();
        rutenett.setLayout(new GridLayout(8,8));
        for (int i = 0; i < 8 * 8; i++) {
            JLabel rute = new JLabel("");
            rute.setPreferredSize(new Dimension(50,50));
            if (i % 2 == 0) {
                rute.setBackground(Color.BLACK);
            } else {
                rute.setBackground(Color.WHITE);
            }
            rute.isOpaque();
            rute.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            rutenett.add(rute);
        }
        vindu.add(rutenett);

        vindu.pack();
        vindu.setVisible(true);
    }
}
