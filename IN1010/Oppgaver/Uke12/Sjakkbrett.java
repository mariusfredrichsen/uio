import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Sjakkbrett {
    public static void main(String[] args) {
        JFrame vindu = new JFrame("Sjakkbrett");
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel rutenett = new JPanel();
        rutenett.setLayout(new GridLayout(8,8));

        for (int i = 0; i < 8*8; i++) {
            JLabel rute = new JLabel("");
            rute.setPreferredSize(new Dimension(30,30));
            if (i % 2 == 0) {
                rute.setBackground(Color.BLACK);
            } else {
                rute.setBackground(Color.WHITE);
            }
            rute.setOpaque(true);
        }
    }
}
