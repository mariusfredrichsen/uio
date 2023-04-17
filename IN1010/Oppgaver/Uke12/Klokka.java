import java.time.LocalTime;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Klokka {

    public static void main(String[] args) {
        JFrame vindu = new JFrame("Klokka");
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        JPanel panel = new JPanel();
        JButton knapp = new JButton(naa());
        panel.add(knapp);
        vindu.add(panel);
        
        class nyTid implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                knapp.setText(naa());
            }
        }
        knapp.addActionListener(new nyTid());

        vindu.pack();
        vindu.setLocationRelativeTo(null);
        vindu.setVisible(true);
    }
    



    private static String naa() {
        // Hva er klokken nå?
        LocalTime t = LocalTime.now();
        return String.format("%02d:%02d:%02d", t.getHour(), t.getMinute(), t.getSecond());
    }
}
