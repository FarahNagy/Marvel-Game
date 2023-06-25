package views;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import engine.Game;
import engine.Player;

public class Start extends JFrame implements ActionListener {
	private String nameFP;
	private String nameSP;
	private Game g;
	private panel1 firstPanel;
	private panel2 secondPanel;
	private panel3 p3;

	public Start() {
		// this.getContentPane().add(firstPanel);
		this.setTitle("Marvel: Ultimate War");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(0, 0, 800, 600);
		this.setVisible(true);
		firstPanel = new panel1(this);
		this.getContentPane().add(firstPanel);
		this.revalidate();
		this.repaint();

	}

	public void switchP(String s1, String s2) {
		nameFP = s1;
		nameSP = s2;
		Player fp = new Player(s1);
		Player sp = new Player(s2);
		g = new Game(fp, sp);
		try {
			g.loadAbilities("Abilities.csv");
			g.loadChampions("Champions.csv");

		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setSize(1000, 680);
		firstPanel.setVisible(false);
		secondPanel = new panel2(this);
		this.getContentPane().add(secondPanel);

	}

	public Game getG() {
		return g;
	}

	public JPanel getFirstPanel() {
		return firstPanel;
	}

	public static void main(String[] args) {
		new Start();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
