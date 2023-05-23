import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUIDialog implements Dialog {
    JFrame vindu;
    JPanel panel;
    JButton ja, nei;
    JLabel sporsmal;
    boolean svar = false;

    GUIDialog() {
        Thread hovedtraad = Thread.currentThread();
        vindu = new JFrame("Bilkollektiv");
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints oppsett = new GridBagConstraints();

        oppsett.gridy = 0;
        sporsmal = new JLabel("Er du bare interessert i elbil?");
        panel.add(sporsmal, oppsett);
        
        class JaKnapp implements ActionListener {
            
            public void actionPerformed(ActionEvent e) {
                settSvar(true);
                hovedtraad.interrupt();
            }
        }

        class NeiKnapp implements ActionListener {

            public void actionPerformed(ActionEvent e) {
                settSvar(false);
                hovedtraad.interrupt();
            }
        }

        oppsett.gridy = 1;
        ja = new JButton("Ja");
        ja.addActionListener(new JaKnapp());
        nei = new JButton("Nei");
        nei.addActionListener(new NeiKnapp());
        panel.add(ja, oppsett);
        panel.add(nei,oppsett);

        vindu.add(panel);
        vindu.setLocationRelativeTo(null);
        vindu.setSize(500,500);
        vindu.setVisible(false);
    }

    public void settSvar(boolean svar) {
        this.svar = svar;
    }

    public boolean svarJaEllerNei(String sporsmal) {
        this.sporsmal.setText(sporsmal);
        vindu.setVisible(true);

        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {}

        vindu.setVisible(false);

        return svar;
    }
}
