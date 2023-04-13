import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class EnkeltVindu {
    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            System.exit(1);
        }

        JFrame vindu = new JFrame("Vindu");
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel panel = new JPanel();
        vindu.add(panel);

        JLabel tekst = new JLabel("En form for setning er her.");
        panel.add(tekst);

        JButton knapp = new JButton("En veldig kul knapp");

        class Stopper implements ActionListener {
            int teller = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                teller++;
                System.out.println("Knappen trykket paa" + teller);
                tekst.setText(teller + "");
            }
        }

        knapp.addActionListener(new Stopper());
        panel.add(knapp);


        vindu.pack();
        vindu.setLocationRelativeTo(null);
        vindu.setVisible(true);
    }
}
