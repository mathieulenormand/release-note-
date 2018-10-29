package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import controllers.ReleaseNote;
import exception.ConfigurationException;

public class Ffff implements ActionListener {

	private JTextField projectField;
	private JTextField versionField;

	public Ffff(JTextField projectField, JTextField versionField) {
		this.projectField = projectField;
		this.versionField = versionField;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		final String project = projectField.getText();
		final String version = versionField.getText();
		try {
			System.out.println("Generating release note");
			new ReleaseNote(project, version).execute();
			System.out.println("Release note generated");
		} catch (ConfigurationException ex) {
			ex.printStackTrace();
		}
	}
}