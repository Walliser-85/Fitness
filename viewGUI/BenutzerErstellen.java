package viewGUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controllerGUI.BenutzerController;
import viewKomponenten.EinstellungenView;

import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BenutzerErstellen extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private String labelText[] = {"Id", "Vorname", "Nachname", "Benutzername", "Passwort", "Passwort Bestätigen", "Berechtigung"};
	private JTextField jtfs[];
	private JLabel lbs[];
	private JButton okButton, cancelButton;
	private JLabel meldung;
	private BenutzerController bc;
	private FitnessManager parent;
	private JComboBox cbBerechtigung;
	private String[] berechtigungen = {"User", "Admin"};
	/**
	 * Create the dialog.
	 */
	public BenutzerErstellen(FitnessManager parent, BenutzerController bc) {
		super(parent, "Benutzer erstellen", true);
		this.bc = bc;
		this.parent = parent;

		setResizable(false);
		getContentPane().setBackground(Color.GRAY);
		getContentPane().setFont(EinstellungenView.standartFont);
		setBounds(100, 100, 900, 700);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		contentPanel.setOpaque(true);

		JLabel lblNeuerKundeErstellen = new JLabel("Neuer Kunde Erstellen");
		lblNeuerKundeErstellen.setOpaque(true);
		lblNeuerKundeErstellen.setFont(EinstellungenView.standartFont);
		lblNeuerKundeErstellen.setBackground(Color.GRAY);
		lblNeuerKundeErstellen.setBorder(new EmptyBorder(10,10,10,0));
		contentPanel.add(lblNeuerKundeErstellen, BorderLayout.NORTH);

		JPanel pnlDashErstellen = new JPanel();
		contentPanel.add(pnlDashErstellen, BorderLayout.CENTER);
		pnlDashErstellen.setBorder(new EmptyBorder(10,10,10,10));
		pnlDashErstellen.setLayout(new GridLayout(8, 2, 10, 10));

		lbs = new JLabel[labelText.length];
		for (int i = 0; i < lbs.length; i++) {
			lbs[i] = new JLabel(labelText[i]+" : ");
			lbs[i].setFont(EinstellungenView.standartFont);
		}

		jtfs = new JTextField[labelText.length-1];
		for (int i = 0; i < jtfs.length; i++) {
			if(i == 0) {
				jtfs[i] = new JTextField(Integer.toString(bc.getCountBenutzer()+1));
			}
			else if(i == 4 || i == 5){
				jtfs[i] = new JPasswordField();
			}else{
				jtfs[i] = new JTextField();
			}
			jtfs[i].setFont(EinstellungenView.standartFont);
		}

		jtfs[0].setEnabled(false);

		meldung = new JLabel("");
		meldung.setFont(EinstellungenView.standartFont);

		cbBerechtigung = new JComboBox(berechtigungen);
		cbBerechtigung.setFont(EinstellungenView.standartFont);

		for(int i=0; i<lbs.length-1;i++) {
			pnlDashErstellen.add(lbs[i]);
			pnlDashErstellen.add(jtfs[i]);
		}

		pnlDashErstellen.add(lbs[lbs.length-1]);
		pnlDashErstellen.add(cbBerechtigung);
		pnlDashErstellen.add(meldung);
		pnlDashErstellen.add(new JLabel(""));

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		MeinActionListener handler = new MeinActionListener();
		okButton = new JButton("Kunde Erstellen");
		okButton.setFont(EinstellungenView.buttonFont);
		okButton.setActionCommand("OK");
		okButton.addActionListener(handler);
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		cancelButton = new JButton("Cancel");
		cancelButton.setFont(EinstellungenView.buttonFont);
		cancelButton.setActionCommand("Cancel");
		cancelButton.addActionListener(handler);
		buttonPane.add(cancelButton);

	}

	private void setMeldung(String meldungStr){
		meldung.setText("<html>"+meldungStr+"</html>");
	}

	private void borderNull() {
		for(int i=0; i< jtfs.length; i++) {
			jtfs[i].setBorder(null);
		}
	}

	private boolean check(){
		borderNull();
		boolean ausgefuellt = true;
		for (int i = 0; i < jtfs.length; i++) {
			if (jtfs[i].getText().equals("")) {
				ausgefuellt = false;
				jtfs[i].setBorder(new LineBorder(Color.RED));
			} 
		}
		return ausgefuellt;
	}

	private class MeinActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == okButton) {
				if(check()) {
					if(jtfs[4].getText().equals(jtfs[5].getText())) {
						setMeldung(bc.speichern(jtfs[0].getText(), jtfs[1].getText(), jtfs[2].getText(), jtfs[3].getText(), jtfs[4].getText(), cbBerechtigung.getSelectedItem().toString(), parent));
					}
					else {
						setMeldung("Passwort stimmt nicht überein");
					}
				}
				else {
					setMeldung("Füllen sie alle Felder aus!");
				}
			}
			if(e.getSource() == cancelButton) {
				dispose();
			}
		}

	}

}
