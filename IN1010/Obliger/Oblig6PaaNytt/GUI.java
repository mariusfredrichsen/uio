import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class GUI {
    Kontroll kontroll;
    JFrame vindu;
    JPanel panel, konsoll, rutenett;
    JButton[][] ruter = new JButton[3][3];
    JLabel status;
    JButton stoppknapp;
    
    class Stoppbehandler implements ActionListener {
	    @Override
	    public void actionPerformed (ActionEvent e) {
		     kontroll.avsluttSpillet();
	    }
	}
	
	class Spillvelger implements ActionListener {
	    int rad, kol;
	    Spillvelger (int r, int k) {
			rad = r;  kol = k;
	    }
		@Override
		public void actionPerformed (ActionEvent e) {
			kontroll.brukervalg(rad, kol);
		}
	}
    
    GUI (Kontroll k) {
	    kontroll = k;	
	    try {
	        UIManager.setLookAndFeel(
	         UIManager.getCrossPlatformLookAndFeelClassName());
	    } catch (Exception e) { System.exit(9); }

        vindu = new JFrame("Tripp trapp tresko");
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    panel = new JPanel();
	    panel.setLayout(new BorderLayout());
	    vindu.add(panel);

	    konsoll = new JPanel();
	    konsoll.setLayout(new BorderLayout());
	    panel.add(konsoll, BorderLayout.NORTH);

	    status = new JLabel("Velg en rute");
	    konsoll.add(status, BorderLayout.NORTH);

	    stoppknapp = new JButton("Exit");
	    stoppknapp.addActionListener(new Stoppbehandler());
	    konsoll.add(stoppknapp, BorderLayout.SOUTH);

	    rutenett = new JPanel();
	    rutenett.setLayout(new GridLayout(3,3));
	    for (int rx = 0;  rx < 3;  ++rx) {
	        for (int kx = 0;  kx < 3;  ++kx) {
		        JButton b = new JButton(" ");
		        ruter[rx][kx] = b;
		        b.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
		        b.addActionListener(new Spillvelger(rx,kx));
		        rutenett.add(b);
	        }
	    }
	    panel.add(rutenett, BorderLayout.CENTER);

	    vindu.pack();
	    vindu.setSize(400,400);
	    vindu.setLocationRelativeTo(null);
	    vindu.setVisible(true);
    }
 
    void markerTrekk (int r, int k, char c) {
	    ruter[r][k].setText(Character.toString(c));
    }

    void visStatus (String tekst) {
	    status.setText(tekst);
    }
}