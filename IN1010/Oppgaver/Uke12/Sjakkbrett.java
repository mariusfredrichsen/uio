import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Sjakkbrett {
    public static void main(String[] args) {
        JFrame vindu = new JFrame("Sjakkbrett");
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel rutenett = new JPanel();
        rutenett.setLayout(new GridLayout(8,8));

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JLabel rute = new JLabel("");
                rute.setPreferredSize(new Dimension(60,60));
                if ((i+j) % 2 == 0) {
                    rute.setBackground(Color.BLACK);
                } else {
                    rute.setBackground(Color.WHITE);
                }
                rute.setOpaque(true);
                rutenett.add(rute);
            }
        }

        vindu.add(rutenett);
        
        vindu.pack();
        vindu.setLocationRelativeTo(null);
        vindu.setVisible(true);
    }
}
