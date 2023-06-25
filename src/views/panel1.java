package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class panel1 extends JPanel implements ActionListener {

	private JTextField firstText;
	private JTextField secondText;
	private Start start;
	private JLabel label1;
	private JLabel label2;
	private JButton OK;

	public panel1(Start s) {
		start = s;
		this.setLayout(null);
		firstText = new JTextField("");
		firstText.setBounds(150, 30, 150, 25);
		label1 = new JLabel("First Player");
		label1.setBounds(30, 30, 120, 25);
		secondText = new JTextField("");
		secondText.setBounds(450, 30, 150, 25);
		label2 = new JLabel("Second Player");
		label2.setBounds(330, 30, 120, 25);
		OK = new JButton("OK");
		OK.setFocusable(false);
		OK.setBounds(250, 100, 120, 30);
		OK.addActionListener(this);
		// this.setBounds(arg0, arg1, arg2, arg3);
		this.add(firstText);
		this.add(secondText);
		this.add(label1);
		this.add(label2);
		this.add(OK);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == OK) {
			if (firstText.getText().equals("")) {
				JOptionPane.showMessageDialog(this,
						"Must enter a name for the first player!", "Error",
						JOptionPane.ERROR_MESSAGE);
			} else {
				if (secondText.getText().equals("")) {
					JOptionPane.showMessageDialog(this,
							"Must enter a name for the second player!",
							"Error", JOptionPane.ERROR_MESSAGE);
				}else
					start.switchP(firstText.getText(), secondText.getText());
			}
		}
	}

}