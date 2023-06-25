package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import engine.Player;
import engine.PriorityQueue;
import exceptions.AbilityUseException;
import exceptions.ChampionDisarmedException;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughResourcesException;
import exceptions.UnallowedMovementException;
import model.abilities.Ability;
import model.abilities.AreaOfEffect;
import model.abilities.CrowdControlAbility;
import model.abilities.DamagingAbility;
import model.abilities.HealingAbility;
import model.world.AntiHero;
import model.world.Champion;
import model.world.Cover;
import model.world.Damageable;
import model.world.Direction;
import model.world.Hero;
import model.world.Villain;

public class panel3 extends JPanel implements ActionListener {
	private Start start;
	private JButton[][] d;
	private JPanel p1;
	private JPanel p2;
	private JTextArea playing;
	private JTextArea deets;
	private JTextArea leftPanText;
	private JPanel leftPanel;
	private JButton leftKey;
	private JButton rightKey;
	private JButton upKey;
	private JButton downKey;
	private JButton move;
	private JButton attack;
	private JButton cast;
	private JButton useLeaderAbilty;
	private JButton endturn;
	private JButton Ability1;
	private JButton Ability2;
	private JButton Ability3;
	private JTextField firstText;
	private JTextField secondText;
	private Direction dir = Direction.LEFT;

	public panel3(Start s) {
		d = new JButton[5][5];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				d[i][j] = new JButton();
			}
		}
		firstText = new JTextField();
		secondText = new JTextField();
		firstText.setBounds(800, 100, 100, 50);
		secondText.setBounds(900, 100, 100, 50);
		start = s;
		leftKey = new JButton("Left");
		rightKey = new JButton("Right");
		upKey = new JButton("Up");
		downKey = new JButton("Down");
		leftKey.addActionListener(this);
		rightKey.addActionListener(this);
		upKey.addActionListener(this);
		downKey.addActionListener(this);

		Ability1 = new JButton();
		Ability2 = new JButton();
		Ability3 = new JButton();
		ability();
		Ability1.setBounds(150, 630, 100, 100);
		Ability2.setBounds(300, 630, 100, 100);
		endturn = new JButton("End turn");
		Ability3.setBounds(450, 630, 100, 100);
		endturn.setBounds(5, 630, 100, 100);
		endturn.addActionListener(this);

		move = new JButton("Move");
		move.setBounds(870, 300, 100, 100);
		move.addActionListener(this);

		attack = new JButton("Attack");
		attack.setBounds(870, 200, 100, 100);

		leftKey.setBounds(770, 600, 100, 100);
		rightKey.setBounds(970, 600, 100, 100);

		upKey.setBounds(870, 500, 100, 100);
		downKey.setBounds(870, 600, 100, 100);
		this.setBounds(0, 0, 750, 600);
		deets = new JTextArea();
		deets.setBounds(1100, 0, 300, 650);
		deets();
		start.getContentPane().add(deets);
		playing = new JTextArea();
		playing.setBounds(0, 600, 300, 30);

		// leftPanel.add(leftPanText, BorderLayout.NORTH);
		// p2.add(leftPanel, BorderLayout.EAST);
		start.getContentPane().add(playing);
		start.getContentPane().add(leftKey);
		start.getContentPane().add(rightKey);
		start.getContentPane().add(upKey);
		start.getContentPane().add(downKey);
		start.getContentPane().add(move);
		start.getContentPane().add(attack);
		start.getContentPane().add(endturn);
		start.getContentPane().add(Ability1);
		start.getContentPane().add(Ability2);
		start.getContentPane().add(Ability3);
		start.getContentPane().add(firstText);
		start.getContentPane().add(secondText);
		// p2.add(leftPanel, BorderLayout.CENTER);
		start.validate();
		start.repaint();
		start.revalidate();
		this.setLayout(new GridLayout(5, 5));
		board();
		this.validate();
		this.repaint();
		this.revalidate();
		start.validate();
		start.repaint();
		start.revalidate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == upKey){
			dir = Direction.UP;
		}
		if(e.getSource() == downKey){
			dir = Direction.DOWN;
		}
		if(e.getSource() == leftKey){
			dir = Direction.LEFT;
		}
		if(e.getSource() == rightKey){
			dir = Direction.RIGHT;
		}
		if (e.getSource() == move) {
				try {
					start.getG().move(dir);
					board();
					deets();
				} catch (NotEnoughResourcesException
						| UnallowedMovementException e1) {
					JOptionPane.showMessageDialog(this, e1.getMessage(), "",
							JOptionPane.ERROR_MESSAGE);
				}
			
						}
	
		if (e.getSource() == attack) {
				try {
					start.getG().attack(dir);
				} catch (NotEnoughResourcesException
						| ChampionDisarmedException | InvalidTargetException e1) {
					JOptionPane.showMessageDialog(this, e1.getMessage(), "",
							JOptionPane.ERROR_MESSAGE);
				}
				board();
				deets();
			
		}
		if (e.getSource() == endturn) {
			start.getG().endTurn();
			board();
			deets();
		}
		if (e.getSource() == Ability1 || e.getSource() == Ability2
				|| e.getSource() == Ability3) {
			Champion c = start.getG().getCurrentChampion();
			for (int i = 0; i < c.getAbilities().size(); i++) {
				if (c.getAbilities().get(i).getName()
						.equals(Ability1.getText())) {
					Ability a = c.getAbilities().get(i);
					if (a.getCastArea() == AreaOfEffect.DIRECTIONAL) {
							try {
								start.getG().castAbility(a, dir);
							} catch (NotEnoughResourcesException
									| AbilityUseException
									| CloneNotSupportedException e1) {
								JOptionPane.showMessageDialog(this,
										e1.getMessage(), "",
										JOptionPane.ERROR_MESSAGE);
							}
							board();
							deets();
						} 
						if (a.getCastArea() == AreaOfEffect.SELFTARGET
								|| a.getCastArea() == AreaOfEffect.SURROUND
								|| a.getCastArea() == AreaOfEffect.TEAMTARGET) {
							try {
								start.getG().castAbility(a);
							} catch (NotEnoughResourcesException
									| AbilityUseException
									| CloneNotSupportedException e1) {
								JOptionPane.showMessageDialog(this,
										e1.getMessage(), "",
										JOptionPane.ERROR_MESSAGE);
							}
							board();
							deets();
						} else {
							if (a.getCastArea() == AreaOfEffect.SINGLETARGET) {
								JOptionPane.showMessageDialog(this,
										"Choose a Target", "",
										JOptionPane.INFORMATION_MESSAGE);
								if (firstText.getText().equals("")
										|| secondText.getText().equals("")) {
									JOptionPane.showMessageDialog(this,
											"Must Enter Co-ordinates", "",
											JOptionPane.ERROR_MESSAGE);
									return;
								}
								try {
									start.getG().castAbility(
											a,
											Integer.parseInt(firstText
													.getText()),
											Integer.parseInt(secondText
													.getText()));
								} catch (NumberFormatException
										| NotEnoughResourcesException
										| AbilityUseException
										| InvalidTargetException
										| CloneNotSupportedException e1) {
									JOptionPane.showMessageDialog(this,
											e1.getMessage(), "",
											JOptionPane.ERROR_MESSAGE);
								}
								board();
								deets();

							}
						}
					}
				}
		}
		Player winner = start.getG().checkGameOver();
		if (winner != null) {
			JOptionPane.showMessageDialog(this,
					"The winner is " + winner.getName(),
					"well done " + winner.getName(),
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void deets() {
		Champion c = start.getG().getCurrentChampion();
		String s = "First Player: " + start.getG().getFirstPlayer().getName()
				+ "\n" + "Second Player: "
				+ start.getG().getSecondPlayer().getName() + "\n"
				+ "Champion Playing: " + c.getName() + "\n" + "    Type: "
				+ champType(c) + "\n" + "    CurrentHP: " + c.getCurrentHP()
				+ "\n" + "    Mana: " + c.getMana() + "\n"
				+ "    Action Points: " + c.getCurrentActionPoints() + "\n"
				+ "    Abilities: 1." + c.getAbilities().get(0).getName()
				+ "\n" + "                Type: "
				+ abilityType(c.getAbilities().get(0)) + "\n"
				+ "                Area of Effect: "
				+ areaEff(c.getAbilities().get(0)) + "\n"
				+ "                Cast Range: "
				+ c.getAbilities().get(0).getCastRange() + "\n"
				+ "                Mana Cost: "
				+ c.getAbilities().get(0).getManaCost() + "\n"
				+ "                Action Cost: "
				+ c.getAbilities().get(0).getRequiredActionPoints() + "\n"
				+ "                Current Cooldown: "
				+ c.getAbilities().get(0).getCurrentCooldown() + "\n"
				+ "                Base Cooldown: "
				+ c.getAbilities().get(0).getBaseCooldown() + "\n"
				+ healDamageEffect(c.getAbilities().get(0)) + "\n"
				+ "                2." + c.getAbilities().get(1).getName()
				+ "\n" + "                Type: "
				+ abilityType(c.getAbilities().get(1)) + "\n"
				+ "                Area of Effect: "
				+ areaEff(c.getAbilities().get(1)) + "\n"
				+ "                Cast Range: "
				+ c.getAbilities().get(1).getCastRange() + "\n"
				+ "                Mana Cost: "
				+ c.getAbilities().get(1).getManaCost() + "\n"
				+ "                Action Cost: "
				+ c.getAbilities().get(1).getRequiredActionPoints() + "\n"
				+ "                Current Cooldown: "
				+ c.getAbilities().get(1).getCurrentCooldown() + "\n"
				+ "                Base Cooldown: "
				+ c.getAbilities().get(1).getBaseCooldown() + "\n"
				+ healDamageEffect(c.getAbilities().get(1)) + "\n"
				+ "                3." + c.getAbilities().get(2).getName()
				+ "\n" + "                Type: "
				+ abilityType(c.getAbilities().get(2)) + "\n"
				+ "                Area of Effect: "
				+ areaEff(c.getAbilities().get(2)) + "\n"
				+ "                Cast Range: "
				+ c.getAbilities().get(2).getCastRange() + "\n"
				+ "                Mana Cost: "
				+ c.getAbilities().get(2).getManaCost() + "\n"
				+ "                Action Cost: "
				+ c.getAbilities().get(2).getRequiredActionPoints() + "\n"
				+ "                Current Cooldown: "
				+ c.getAbilities().get(2).getCurrentCooldown() + "\n"
				+ "                Base Cooldown: "
				+ c.getAbilities().get(2).getBaseCooldown() + "\n"
				+ healDamageEffect(c.getAbilities().get(2)) + "\n"
				+ getAppEff(c) + "\n" + "    Attack Damage: "
				+ c.getAttackDamage() + "\n" + "    Attack Range: "
				+ c.getAttackRange() + "\n" + leaderAbilityUsedOrNot();
		deets.setText(s);
		// whoIsPlayingAndWhoIsNext();

	}

	public String champType(Champion c) {
		String s;
		if (c instanceof Hero)
			s = "Hero";
		else {
			if (c instanceof Villain)
				s = "Villain";
			else
				s = "Anti-Hero";
		}
		return s;

	}

	public String abilityType(Ability a) {
		String s;
		if (a instanceof CrowdControlAbility)
			s = "CC Ability";
		else {
			if (a instanceof DamagingAbility)
				s = "Damaging Ability";
			else
				s = "Healing Ability";
		}
		return s;

	}

	public String areaEff(Ability a) {
		String s;
		if (a.getCastArea() == AreaOfEffect.DIRECTIONAL)
			s = "Directional";
		else {
			if (a.getCastArea() == AreaOfEffect.SELFTARGET)
				s = "Self-Target";
			else {
				if (a.getCastArea() == AreaOfEffect.SINGLETARGET)
					s = "Single-Target";
				else
					s = "Surround";

			}

		}
		return s;

	}

	public String healDamageEffect(Ability a) {
		String s;
		if (a instanceof CrowdControlAbility)
			s = "                Effect: "
					+ ((CrowdControlAbility) a).getEffect().getName();
		else {
			if (a instanceof DamagingAbility)
				s = "                Damage Amount: "
						+ ((DamagingAbility) a).getDamageAmount();
			else
				s = "                Healing Ability"
						+ ((HealingAbility) a).getHealAmount();
		}
		return s;

	}

	public String getAppEff(Champion c) {
		String s = "    Applied Effects: ";
		for (int i = 1; i <= c.getAppliedEffects().size(); i++) {
			s += i + "." + c.getAppliedEffects().get(i - 1).getName() + "("
					+ c.getAppliedEffects().get(i - 1).getDuration()
					+ " turns)" + "\n";
			if (i != c.getAppliedEffects().size())
				s += "                ";
		}
		return s;
	}

	public String getAppEff2(Champion c) {
		String s = ", Effects: ";
		for (int i = 1; i <= c.getAppliedEffects().size(); i++) {
			s += c.getAppliedEffects().get(i - 1).getName() + "("
					+ c.getAppliedEffects().get(i - 1).getDuration()
					+ " turns)";
			if (i != c.getAppliedEffects().size())
				s += ", ";
		}
		return s;
	}

	public String RemainingChamps(Champion c) {
		String s = "";
		s += c.getName() + "(HP: " + c.getCurrentHP() + ", Mana: "
				+ c.getMana() + ", speed: " + c.getSpeed()
				+ ", Max action points: " + c.getMaxActionPointsPerTurn()
				+ ", Attack Damage: " + c.getAttackDamage() + ", Range: "
				+ c.getAttackRange() + ", Type: " + champType(c)
				+ getAppEff2(c);
		if (start.getG().getFirstPlayer().getLeader() == c)
			s += ", Leader";
		s += ")";

		return s;
	}

	public String leaderAbilityUsedOrNot() {
		String s = "First Player's Leader Ability: ";
		if (start.getG().isFirstLeaderAbilityUsed()) {
			s += "Used";
		} else
			s += "Not Used";
		s += "\n" + "Second Player's Leader Ability: ";
		if (start.getG().isSecondLeaderAbilityUsed()) {
			s += "Used";
		} else
			s += "Not Used";
		return s;
	}

	public void whoIsPlayingAndWhoIsNext() {
		String s = "Next up is: " + nextUp();
		playing.setText(s);
	}

	public void board() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				d[i][j].setText("");
				this.remove(d[i][j]);

			}
		}

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				JButton b = d[i][j];
				if (start.getG().getBoard()[i][j] instanceof Champion) {
					b.addActionListener(this);
					Champion c = (Champion) start.getG().getBoard()[i][j];
					b.setText(c.getName());
					if (start.getG().getFirstPlayer().getTeam().contains(c))
						b.setBackground(new Color(123, 106, 174));
					else
						b.setBackground(Color.YELLOW);
					if (c == start.getG().getCurrentChampion())
						b.setBackground(Color.RED);
					b.setToolTipText(RemainingChamps(c));
				} else {
					if (start.getG().getBoard()[i][j] instanceof Cover) {
						b.addActionListener(this);
						b.setText("Cover");
						b.setBackground(new Color(78, 91, 49));
					} else
						b.setBackground(Color.WHITE);
				}
				this.add(b);
			}
		}
		this.validate();
		this.repaint();
		this.revalidate();
		start.validate();
		start.repaint();
		start.revalidate();
	}

	public String nextUp() {
		PriorityQueue q = new PriorityQueue(start.getG().getTurnOrder().size());
		Champion temp = (Champion) start.getG().getTurnOrder().remove();
		String s = ((Champion) start.getG().getTurnOrder().peekMin()).getName();
		q.insert(temp);
		for (int i = 0; i < start.getG().getTurnOrder().size(); i++) {
			q.insert(start.getG().getTurnOrder().remove());
		}
		for (int i = 0; i < q.size(); i++) {
			start.getG().getTurnOrder().insert(q.remove());

		}
		return s;
	}

	public void ability() {
		String a1 = start.getG().getCurrentChampion().getAbilities().get(0)
				.getName();
		String a2 = start.getG().getCurrentChampion().getAbilities().get(1)
				.getName();
		String a3 = start.getG().getCurrentChampion().getAbilities().get(2)
				.getName();
		Ability1.setText(a1);
		Ability2.setText(a2);
		Ability3.setText(a3);
	}
}
