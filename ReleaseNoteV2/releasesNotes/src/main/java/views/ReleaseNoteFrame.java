package views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ReleaseNoteFrame extends JFrame {

	public ReleaseNoteFrame() {
		this.setTitle("Release note");
		this.setSize(600, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null); // Centre fenêtre dans écran

		JPanel container = new JPanel();
		container.setLayout(new GridBagLayout());

		container.add(new JLabel("RELEASES NOTES JIRA ARCAD"), new GridBagConstraints(0, 0, 2, 1, 0, 0,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, createInsets(), 0, 0));

		container.add(new JLabel("Project"), new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, createInsets(), 0, 0));
		final JTextField projetField = new JTextField("ARCAD");
		container.add(projetField, new GridBagConstraints(1, 1, 2, 1, 1.0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, createInsets(), 0, 0));

		container.add(new JLabel("version"), new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, createInsets(), 0, 0));
		final JTextField versionField = new JTextField("version-1.1");
		container.add(versionField, new GridBagConstraints(1, 2, 1, 1, 1.0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, createInsets(), 0, 0));

		container.add(Box.createGlue(), new GridBagConstraints(0, 3, 0, 0, 0, 1.0, GridBagConstraints.CENTER,
				GridBagConstraints.VERTICAL, createInsets(), 0, 0));

		final JButton button = new JButton("Submit");
		container.add(button, new GridBagConstraints(0, 4, 2, 1, 0, 0, GridBagConstraints.SOUTHEAST,
				GridBagConstraints.NONE, createInsets(), 0, 0));

		ActionListener l = new Ffff(projetField, versionField);
		button.addActionListener(l);

		this.setContentPane(container);
		this.setVisible(true);
	}

	private Insets createInsets() {
		return new Insets(10, 10, 10, 10);
	}
}
