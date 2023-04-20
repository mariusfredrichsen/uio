import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class GUI {
    Kontroll kontroll;
    JFrame vindu;
    JPanel valgPanel, hovedPanel, rutenett, topPanel;
    JButton[][] celleKnapper;
    JButton start;
	JButton pause;
	JButton avslutt;
    JLabel antLevende;
	int rad;
	int kol;
	Thread traad;

	class ByttStatus implements ActionListener {
		boolean trykket = false;
		int rad, kol;

		ByttStatus(int rad, int kol) {
			this.rad = rad;
			this.kol = kol;
		}

		public void actionPerformed(ActionEvent e) {
			kontroll.byttStatus(rad, kol);
		}
	}
    
    class Start implements ActionListener {
	    @Override
	    public void actionPerformed (ActionEvent e) {
			traad = new Thread(new EnFuckaTraad(kontroll, kontroll.gui));
			traad.start();
	    }
	}

	class Pause implements ActionListener {
		boolean trykket = false;
		@Override
		public void actionPerformed(ActionEvent e) {
			trykket = !trykket;
			if (trykket) pause.setText("Gjenoppta");
			else pause.setText("Pause");
		}
	}
	
	class Avslutt implements ActionListener {
		@Override
		public void actionPerformed (ActionEvent e) {
			kontroll.avslutt();
		}
	}

	GUI(Kontroll kontroll) {
		this.kontroll = kontroll;
		rad = kontroll.hentRad();
		kol = kontroll.hentKol();
		celleKnapper = new JButton[rad][kol];

		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			System.exit(1);
		}

		vindu = new JFrame("Game of life");
		vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		hovedPanel = new JPanel(new GridBagLayout());
		GridBagConstraints oppsett = new GridBagConstraints();

		topPanel = new JPanel(new FlowLayout());
		antLevende = new JLabel("Antall levende: 0");
		topPanel.add(antLevende);

		start = new JButton("Start");
		start.addActionListener(new Start());
		topPanel.add(start);

		pause = new JButton("Gjenoppta");
		pause.setPreferredSize(pause.getPreferredSize());
		pause.setText("Pause");
		pause.addActionListener(new Pause());
		topPanel.add(pause);

		avslutt = new JButton("Avslutt");
		avslutt.addActionListener(new Avslutt());
		topPanel.add(avslutt);
		oppsett.gridy = 0;
		hovedPanel.add(topPanel, oppsett);

		oppsett.gridy = 1;
		rutenett = new JPanel(new GridLayout(rad, kol));
		for (int r = 0; r < rad; r++) {
			for (int k = 0; k < kol; k++) {
				JButton celleKnapp = new JButton("");
				celleKnapp.setPreferredSize(new Dimension(50,50));
				celleKnapp.setOpaque(true);
				celleKnapp.setBackground(Color.WHITE);
				celleKnapp.addActionListener(new ByttStatus(r, k));
				celleKnapper[r][k] = celleKnapp;
				rutenett.add(celleKnapp);
			}
		}
		hovedPanel.add(rutenett, oppsett);
		vindu.add(hovedPanel);

		vindu.pack();
		vindu.setLocationRelativeTo(null);
		vindu.setVisible(true);
		System.out.println("asdasd");
	}

	public void byttStatus(int rad, int kol, boolean status) {
		if (status) celleKnapper[rad][kol].setBackground(Color.BLACK);
		else celleKnapper[rad][kol].setBackground(Color.WHITE);
	}

	public void oppdater(Celle[][] celler) {
		antLevende.setText("Antall levende: " + kontroll.hentAntLevende());
		for (int r = 0; r < rad; r++) {
			for (int k = 0; k < kol; k++) {
				if (celler[r][k].erLevende()) celleKnapper[r][k].setBackground(Color.BLACK);
				else celleKnapper[r][k].setBackground(Color.WHITE);
			}
		}
	}
}