import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.*;

public class EnkeltRutenett {
    public static void main(String[] args) {
        JFrame vindu = new JFrame("Vindu");
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        vindu.add(panel);
        
        JPanel rutenett = new JPanel();
        rutenett.setLayout(new GridLayout(3,3));
        for (int i = 0; i < 9; i++) {
            JLabel nummer = new JLabel("" + i);
            nummer.setPreferredSize(new Dimension(9*20,9*20));
            nummer.setHorizontalAlignment(JLabel.LEFT);
            nummer.setVerticalAlignment(JLabel.CENTER);
            nummer.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            rutenett.add(nummer);
        }
        vindu.add(rutenett);

        vindu.pack();
        vindu.setVisible(true);
    }
}
