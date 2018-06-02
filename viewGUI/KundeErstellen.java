package viewGUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controllerGUI.KundeController;
import viewKomponenten.EinstellungenView;

import java.awt.Font;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KundeErstellen extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private String labelText[] = {"Id", "Vorname", "Nachname", "Adresse", "PLZ", "Ort", "Email"};
	private JTextField jtfs[];
	private JLabel lbs[];
	private JButton okButton, cancelButton;
	private JLabel meldung;
	private KundeController kc;
	private FitnessManager parent;
	/**
	 * Create the dialog.
	 */
	public KundeErstellen(FitnessManager parent, KundeController kc) {
		super(parent, "Kunde erstellen", true);
		this.kc = kc;
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
		
		JLabel lblNeuerBenutzerErstellen = new JLabel("Neuer Benutzer Erstellen");
		lblNeuerBenutzerErstellen.setOpaque(true);
		lblNeuerBenutzerErstellen.setFont(EinstellungenView.standartFont);
		lblNeuerBenutzerErstellen.setBackground(Color.GRAY);
		lblNeuerBenutzerErstellen.setBorder(new EmptyBorder(10,10,10,0));
		contentPanel.add(lblNeuerBenutzerErstellen, BorderLayout.NORTH);
		
		JPanel pnlDashErstellen = new JPanel();
		contentPanel.add(pnlDashErstellen, BorderLayout.CENTER);
		pnlDashErstellen.setBorder(new EmptyBorder(10,10,10,10));
		pnlDashErstellen.setLayout(new GridLayout(8, 2, 10, 10));
		
		lbs = new JLabel[labelText.length];
		for (int i = 0; i < lbs.length; i++) {
			lbs[i] = new JLabel(labelText[i]+" : ");
			lbs[i].setFont(EinstellungenView.standartFont);
		}
		
		jtfs = new JTextField[labelText.length];
		for (int i = 0; i < jtfs.length; i++) {
			if(i == 0) {
				jtfs[i] = new JTextField(Integer.toString(kc.getCountKunde()+1));
			}
			else {
				jtfs[i] = new JTextField();
			}
			jtfs[i].setFont(EinstellungenView.standartFont);
		}
		
		jtfs[0].setEnabled(false);
		
		meldung = new JLabel("");
		meldung.setFont(EinstellungenView.standartFont);
		
		for(int i=0; i<lbs.length;i++) {
			pnlDashErstellen.add(lbs[i]);
			pnlDashErstellen.add(jtfs[i]);
		}
		
		pnlDashErstellen.add(meldung);
		pnlDashErstellen.add(new JLabel(""));
			
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		MeinActionListener handler = new MeinActionListener();
		okButton = new JButton("Benutzer Erstellen");
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
					try {
						setMeldung(kc.speichern(jtfs[0].getText(), jtfs[1].getText(), jtfs[2].getText(), jtfs[3].getText(), 
								jtfs[4].getText(), jtfs[5].getText(), jtfs[6].getText(),parent));
					} catch (NumberFormatException ex) {
						setMeldung("PLZ ist keine Nummer!!");
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
