package viewGUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import model.Muskelgruppe;
import viewKomponenten.EinstellungenView;

import java.awt.Font;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import controllerGUI.MuskelgruppeController;

public class MuskelgruppeErstellen extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private String labelText[] = {"Id", "Muskelgruppe", "Beschreibung"};
	private JTextField jtfs[];
	private JLabel lbs[];
	private JButton okButton, cancelButton, speichernButton;
	private JLabel meldung;
	private MuskelgruppeController mc;
	private FitnessManager frame;
	private Muskelgruppe muskel;
	private JLabel lblMuskelgruppeErstellen;

	/**
	 * Create the dialog.
	 */	
	public MuskelgruppeErstellen(FitnessManager parent, MuskelgruppeController mc, int selectedRowMuskelgruppe) {
		super(parent, "Muskelgruppe Erstellen", true);
		this.frame = parent;
		this.mc = mc;
		if(selectedRowMuskelgruppe != -1) {
			muskel = mc.getMuskelgruppe(selectedRowMuskelgruppe);
			lblMuskelgruppeErstellen = new JLabel("Muskelgruppe Bearbeiten");
			setTitle("Muskelgruppe Bearbeiten");
		}
		else {
			lblMuskelgruppeErstellen = new JLabel("Neue Muskelgruppe Erstellen");
			setTitle("Muskelgruppe Erstellen");
		}

		setResizable(false);
		setBounds(100, 100, 900, 700);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setOpaque(true);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));

		
		lblMuskelgruppeErstellen.setBackground(Color.GRAY);
		lblMuskelgruppeErstellen.setBorder(new EmptyBorder(10,10,10,0));
		lblMuskelgruppeErstellen.setOpaque(true);
		lblMuskelgruppeErstellen.setFont(EinstellungenView.standartFont);
		contentPanel.add(lblMuskelgruppeErstellen, BorderLayout.NORTH);

		JPanel pnlDashErstellen = new JPanel();
		contentPanel.add(pnlDashErstellen, BorderLayout.CENTER);
		pnlDashErstellen.setBorder(new EmptyBorder(10,10,10,10));
		pnlDashErstellen.setLayout(new GridLayout(4, 2, 10, 10));
		
		lbs = new JLabel[labelText.length];
		for (int i = 0; i < lbs.length; i++) {
			lbs[i] = new JLabel(labelText[i]+" : ");
			lbs[i].setFont(EinstellungenView.standartFont);
		}
		
		jtfs = new JTextField[labelText.length];
		if(selectedRowMuskelgruppe != -1) {
			jtfs[0] = new JTextField(Integer.toString(muskel.getId()));
			jtfs[1] = new JTextField(muskel.getName());
			jtfs[2] = new JTextField(muskel.getBeschreibung());
		}
		else {
			for (int i = 0; i < jtfs.length; i++) {
				if(i == 0) {
					jtfs[i] = new JTextField(Integer.toString(mc.getCountMuskelgruppen()+1));
				}
				else {
					jtfs[i] = new JTextField();
				}
				jtfs[i].setFont(EinstellungenView.standartFont);
			}
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
		
		if(selectedRowMuskelgruppe == -1) {
			okButton = new JButton("Muskelgruppe Erstellen");
			okButton.setFont(EinstellungenView.buttonFont);
			okButton.setActionCommand("OK");
			okButton.addActionListener(handler);
			buttonPane.add(okButton);
			getRootPane().setDefaultButton(okButton);
		}else {
			speichernButton = new JButton("Muskelgruppe Speichern");
			speichernButton.setFont(EinstellungenView.buttonFont);
			speichernButton.setActionCommand("OK");
			speichernButton.addActionListener(handler);
			buttonPane.add(speichernButton);
			getRootPane().setDefaultButton(speichernButton);
		}
		

		cancelButton = new JButton("Cancel");
		cancelButton.setFont(EinstellungenView.buttonFont);
		cancelButton.setActionCommand("Cancel");
		cancelButton.addActionListener(handler);
		buttonPane.add(cancelButton);
	}

	private void setMeldung(String meldungStr) {
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
					setMeldung(mc.speichern(jtfs[0].getText(), jtfs[1].getText(), jtfs[2].getText(),frame));
				}
				else {
					setMeldung("Füllen sie alle Felder aus!");
				}
			}
			if(e.getSource() == speichernButton) {
				if(check()) {
					setMeldung(mc.speichernMuskelgruppe(jtfs[0].getText(), jtfs[1].getText(), jtfs[2].getText(),frame));
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
