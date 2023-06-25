package views;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import engine.Game;

public class quiz extends JFrame implements ActionListener {
	private JButton b;
	private JPanel p2;
	private Game g;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private int i;;
	private int c = 1;

	public quiz() {
		i = (int) (Math.random() * 15);
		try {
			g.loadAbilities("Abilities.csv");
			g.loadChampions("Champions.csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		b = new JButton();
		b.addActionListener(this);
		p2 = new JPanel();
		p2.setLayout(new GridLayout(0, 3));
		button1 = new JButton();
		button1.addActionListener(this);
		button2 = new JButton();
		button2.addActionListener(this);
		button3 = new JButton();
		button3.addActionListener(this);
		p2.add(button1);
		p2.add(button2);
		p2.add(button3);
		b.setText(g.getAvailableChampions().get(i).getName());
		this.setTitle("Quiz");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.add(b, BorderLayout.NORTH);
		this.add(p2, BorderLayout.CENTER);
		this.setBounds(0, 0, 800, 300);
		this.setVisible(true);
		this.revalidate();
		this.repaint();
	}

	public static void main(String[] args) {
		new quiz();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b && c == 1) {
			button1.setText(g.getAvailableChampions().get(i).getAbilities()
					.get(0).getName());
			c++;
		} else {
			if (e.getSource() == b && c == 2) {
				button2.setText(g.getAvailableChampions().get(i).getAbilities()
						.get(1).getName());
				c++;
			} else {
				if (e.getSource() == b && c == 3) {
					button3.setText(g.getAvailableChampions().get(i)
							.getAbilities().get(2).getName());
					c++;
				} else {
					if (e.getSource() == b && c == 4) {
						i = (int) (Math.random() * 15);
						b.setText(g.getAvailableChampions().get(i).getName());
						button1.setText("");
						button2.setText("");
						button3.setText("");
						c++;
					} else {
						if (e.getSource() == b && c == 5) {
							button1.setText(g.getAvailableChampions().get(i)
									.getAbilities().get(0).getName());
							c = 1;
						}
					}
				}

			}
		}

	}
}
