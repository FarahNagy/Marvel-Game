package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import model.world.Champion;
import model.world.Damageable;

public class panel2 extends JPanel implements ActionListener {
	JLabel fpName;
	JLabel spName;
	private JPanel champstochoose;
	private JPanel champstochoose2;
	private Start start;
	private panel3 p3;
	private String nameFP;
	private String nameSP;
	private ArrayList<JButton> buttons;
	private JTextArea ChampDetails;
	private JButton choose;
	private JPanel chooseP;
	private JTextArea ChampSelection;
	private String champSel;
	int c;
	int k;
	ArrayList<JButton> firstPlayersTeamButtons;
	ArrayList<JButton> secondPlayersTeamButtons;
	JButton gamestart;
	ArrayList<Integer> indices;

	public panel2(Start s) {
		champstochoose = new JPanel();
		champstochoose2 = new JPanel();
		indices = new ArrayList();
		gamestart = new JButton("Start Game");
		gamestart.setBounds(1200, 600, 150, 100);
		gamestart.addActionListener(this);
		start = s;
		this.setLayout(new GridLayout(0, 3));
		// this.setPreferredSize(new Dimension(600,start.getHeight()));
		ChampDetails = new JTextArea();
		// ChampDetails.setPreferredSize(new Dimension(200,start.getHeight()));
		ChampDetails.setEditable(false);
		// start.add(ChampDetails, BorderLayout.EAST);
		firstPlayersTeamButtons = new ArrayList<JButton>();
		secondPlayersTeamButtons = new ArrayList<JButton>();
		buttons = new ArrayList<JButton>();
		for (int i = 0; i < s.getG().getAvailableChampions().size(); i++) {
			JButton b = new JButton();
			buttons.add(b);
			b.addActionListener(this);
			Champion champ = start.getG().getAvailableChampions().get(i);
			String info = "Name: " + champ.getName() + "\n" + "Current HP: "
					+ champ.getCurrentHP() + "\n" + "Mana: " + champ.getMana()
					+ "\n" + "Current Action Points: "
					+ champ.getCurrentActionPoints() + "\n" + "Attack Range: "
					+ champ.getAttackRange() + "\n" + "Attack Damage: "
					+ champ.getAttackDamage() + "\n" + "Speed: "
					+ champ.getSpeed() + "\n" + "Abilities: " + "\n";
			int j = 0;
			String l = "";
			while (j < champ.getAbilities().size()) {
				l = l + champ.getAbilities().get(j).getName() + "\n";
				j++;
			}
			b.setToolTipText(info + l);
			b.setText(s.getG().getAvailableChampions().get(i).getName());
			this.add(b);
		}
		ChampSelection = new JTextArea();
		ChampSelection.setEditable(false);
		champSel = "First Player's Team: " + "\n";
		ChampSelection.setText(champSel);
		c = 0;
		k = 0;
		choose = new JButton();
		choose.setText("choose");
		choose.setSize(50, 50);
		choose.addActionListener(this);
		chooseP = new JPanel();
		chooseP.setLayout(new BorderLayout());
		// b.setSize(100,150);
		// chooseP.add(b);
		chooseP.add(ChampSelection);
		start.getContentPane().add(chooseP, BorderLayout.EAST);
		start.getContentPane().add(choose, BorderLayout.PAGE_END);
		fpName = new JLabel(start.getG().getFirstPlayer().getName());
		spName = new JLabel(start.getG().getSecondPlayer().getName());
		this.revalidate();
		this.repaint();
		start.revalidate();
		start.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (buttons.contains((JButton) e.getSource())) {
			if (c < 3) {
				JButton chosen = (JButton) e.getSource();
				int r = buttons.indexOf(chosen);
				Champion champ = start.getG().getAvailableChampions().get(r);
				start.getG().getFirstPlayer().getTeam().add(champ);
				indices.add(r);
				buttons.get(r).setVisible(false);
				champSel += champ.getName() + "\n" + "\n";
				ChampSelection.setText(champSel);
				c++;
			}
			if (c == 3) {
				champSel += "\n" + "\n" + "\n" + "\n"
						+ "Second Player's Team: " + "\n";
				ChampSelection.setText(champSel);
				c++;
			} else if (c > 3) {
				if (k < 3) {
					JButton chosen = (JButton) e.getSource();
					int r = buttons.indexOf(chosen);
					Champion champ = start.getG().getAvailableChampions()
							.get(r);
					start.getG().getSecondPlayer().getTeam().add(champ);
					indices.add(r);
					buttons.get(r).setVisible(false);
					champSel += champ.getName() + "\n" + "\n";
					ChampSelection.setText(champSel);
					k++;
				}
			}

		} else {
			if (e.getSource() == choose) {
				if (k >= 3 && c >= 3) {
					chooseLeader();
				//	JOptionPane.showMessageDialog(this, "Choose your Leader",
					//		"", JOptionPane.INFORMATION_MESSAGE);

				} else {
					JOptionPane.showMessageDialog(this,
							"Each Player Must Choose 3 Champions!", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				if (firstPlayersTeamButtons.contains((JButton) e.getSource())) {
					for (int j = 0; j < firstPlayersTeamButtons.size(); j++) {
						firstPlayersTeamButtons.get(j).setBackground(
								Color.WHITE);
					}
					String s = ((JButton) e.getSource()).getText();
					firstPlayersTeamButtons.get(
							firstPlayersTeamButtons.indexOf(e.getSource()))
							.setBackground(Color.YELLOW);
					for (int k = 0; k < 3; k++) {
						ArrayList<Champion> ft = start.getG().getFirstPlayer()
								.getTeam();
						if (ft.get(k).getName().equals(s)) {
							start.getG().getFirstPlayer().setLeader(ft.get(k));
						}
					}
				} else {
					if (secondPlayersTeamButtons.contains((JButton) e
							.getSource())) {
						for (int j = 0; j < secondPlayersTeamButtons.size(); j++) {
							secondPlayersTeamButtons.get(j).setBackground(
									Color.WHITE);
						}
						String s = ((JButton) e.getSource()).getText();
						secondPlayersTeamButtons
								.get(secondPlayersTeamButtons.indexOf(e
										.getSource())).setBackground(
										Color.YELLOW);
						for (int k = 0; k < 3; k++) {
							ArrayList<Champion> ft = start.getG()
									.getSecondPlayer().getTeam();
							if (ft.get(k).getName().equals(s)) {
								start.getG().getSecondPlayer()
										.setLeader(ft.get(k));
							}
						}
					} else if (e.getSource() == gamestart) {
						if (start.getG().getFirstPlayer().getLeader() == null
								|| start.getG().getSecondPlayer().getLeader() == null) {
							JOptionPane
									.showMessageDialog(
											this,
											"Each Player Must Choose a Leader for their Team!",
											"", JOptionPane.ERROR_MESSAGE);
						} else {

							start.getG().placeChampions();
							start.getG().placeCovers();
							prepare();
							this.remove(champstochoose);
							this.remove(champstochoose2);
							this.remove(gamestart);
							this.remove(fpName);
							this.remove(spName);
							this.setVisible(false);
							// this.setBackground(Color.BLACK);
							start.setLayout(null);
							start.getContentPane().setBackground(Color.white);
							p3 = new panel3(start);
							start.getContentPane().add(p3);
							start.validate();
							start.repaint();
						}
					}
				}
			}
		}
	}

	public void chooseLeader() {
		for (int z = 0; z < buttons.size(); z++) {
			this.remove(buttons.get(z));
		}
		this.setLayout(null);
		chooseP.setVisible(false);
		choose.setVisible(false);
		start.setLayout(null);
		this.setBounds(0, 0, 1500, 1500);
		fpName.setBounds(300, 50, 200, 200);
		fpName.setFont(new Font("Serif", Font.BOLD, 30));
		// champstochoose = new JPanel();
		champstochoose.setLayout(new GridLayout(0, 3));
		champstochoose.setBounds(100, 200, 400, 300);
		for (int i = 0; i < start.getG().getFirstPlayer().getTeam().size(); i++) {
			Champion champ = start.getG().getFirstPlayer().getTeam().get(i);
			JButton b = new JButton(champ.getName());
			b.setBackground(Color.WHITE);
			b.addActionListener(this);
			champstochoose.add(b);
			firstPlayersTeamButtons.add(b);
		}
		spName.setBounds(700, 50, 200, 200);
		spName.setFont(new Font("Serif", Font.BOLD, 30));
		champstochoose2.setLayout(new GridLayout(0, 3));
		champstochoose2.setBounds(600, 200, 400, 300);
		for (int i = 0; i < start.getG().getSecondPlayer().getTeam().size(); i++) {
			Champion champ = start.getG().getSecondPlayer().getTeam().get(i);
			JButton b = new JButton(champ.getName());
			b.setBackground(Color.WHITE);
			b.addActionListener(this);
			champstochoose2.add(b);
			secondPlayersTeamButtons.add(b);
		}
		this.add(champstochoose);
		this.add(fpName);
		this.add(champstochoose2);
		this.add(spName);
		this.add(gamestart);
		this.revalidate();
		this.repaint();
		start.revalidate();
		start.repaint();
	}

	public void prepare() {
		for (Champion c : start.getG().getFirstPlayer().getTeam())
			start.getG().getTurnOrder().insert(c);
		for (Champion c : start.getG().getSecondPlayer().getTeam())
			start.getG().getTurnOrder().insert(c);
	}
}