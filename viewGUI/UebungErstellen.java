package viewGUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controllerGUI.MuskelgruppeController;
import controllerGUI.UebungController;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;

import model.Uebung;
import viewKomponenten.EinstellungenView;

import javax.swing.DefaultComboBoxModel;

public class UebungErstellen extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private String labelText[] = {"Id", "Name", "Beschreibung", "Muskel"};
	private JTextField jtfs[];
	private JLabel lbs[];
	private JButton btnUebungErstellen, btnUebungSpeichern, cancelButton;
	private JLabel meldung;
	private JComboBox muskelgruppen;
	private UebungController uc;
	private MuskelgruppeController mc;
	private FitnessManager parent;
	private UebungErstellen frameUebung;
	private Uebung uebung;
	private JLabel lblNeuebungErstellen;
	
	public UebungErstellen(FitnessManager parent, UebungController uc, int selectedRow) {
		super(parent, "\\u00DCbung erstellen", true);
		frameUebung = this;
		this.uc = uc;
		this.parent = parent;
		if(selectedRow != -1) {
			uebung = uc.getUebung(selectedRow);
		}
				
		mc = new MuskelgruppeController();
		
		setResizable(false);
		if(selectedRow == -1) {
			setTitle("\u00DCbung erstellen");
		}
		else {
			setTitle("\u00DCbung bearbeiten");
		}
		setBounds(100, 100, 900, 700);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setOpaque(true);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		if(selectedRow == -1) {
			lblNeuebungErstellen = new JLabel("Neue \u00DCbung erstellen");
		}
		else {
			lblNeuebungErstellen = new JLabel("\u00DCbung bearbeiten");
		}
		lblNeuebungErstellen.setBorder(new EmptyBorder(10,10,10,0));
		lblNeuebungErstellen.setOpaque(true);
		lblNeuebungErstellen.setForeground(Color.BLACK);
		lblNeuebungErstellen.setBackground(Color.GRAY);
		lblNeuebungErstellen.setFont(EinstellungenView.standartFont);
		contentPanel.add(lblNeuebungErstellen, BorderLayout.NORTH);
		
		JPanel pnlDashErstellen = new JPanel();
		pnlDashErstellen.setBorder(new EmptyBorder(10,10,10,10));
		contentPanel.add(pnlDashErstellen, BorderLayout.CENTER);
		pnlDashErstellen.setLayout(new GridLayout(0, 2, 0, 60));
			
		lbs = new JLabel[labelText.length];
		for (int i = 0; i < lbs.length; i++) {
			lbs[i] = new JLabel(labelText[i]+" : ");
			lbs[i].setFont(EinstellungenView.standartFont);
		}
		jtfs = new JTextField[labelText.length-1];
		if(selectedRow == -1) {
			for (int i = 0; i < jtfs.length; i++) {
				if(i == 0) {
					jtfs[i] = new JTextField(Integer.toString(uc.getCountUebungen()+1));
				}
				else {
					jtfs[i] = new JTextField();
				}
				jtfs[i].setFont(EinstellungenView.standartFont);
			}
		}
		else {
			jtfs[0]= new JTextField(Integer.toString(uebung.getId()));
			jtfs[1]= new JTextField(uebung.getName());
			jtfs[2]= new JTextField(uebung.getBeschreibung());
		}

		jtfs[0].setEnabled(false);
		
		meldung = new JLabel("");
		meldung.setFont(EinstellungenView.standartFont);
		muskelgruppen = new JComboBox(mc.getMuskelgruppenListeCb());
		if(selectedRow != -1) {
			muskelgruppen.setSelectedIndex(mc.getIdFromMuskelgruppe(uebung.getMuskel().getName())-1);
		}
		
		for(int i=0; i<lbs.length-1;i++) {
			pnlDashErstellen.add(lbs[i]);
			pnlDashErstellen.add(jtfs[i]);
		}
		
		pnlDashErstellen.add(lbs[3]);
		pnlDashErstellen.add(muskelgruppen);
		
		pnlDashErstellen.add(meldung);
		pnlDashErstellen.add(new JLabel(""));
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		MeinActionListener handler = new MeinActionListener();
		
		if(selectedRow == -1) {
			btnUebungErstellen = new JButton("Übung Erstellen");
			btnUebungErstellen.setFont(EinstellungenView.buttonFont);
			btnUebungErstellen.setActionCommand("OK");
			btnUebungErstellen.addActionListener(handler);
			buttonPane.add(btnUebungErstellen);
			getRootPane().setDefaultButton(btnUebungErstellen);
		}
		else {
			btnUebungSpeichern = new JButton("Übung Speichern");
			btnUebungSpeichern.setFont(EinstellungenView.buttonFont);
			btnUebungSpeichern.setActionCommand("OK");
			btnUebungSpeichern.addActionListener(handler);
			buttonPane.add(btnUebungSpeichern);
			getRootPane().setDefaultButton(btnUebungSpeichern);
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
			if(e.getSource() == btnUebungErstellen)	{
				if(check()) {
					setMeldung(uc.speichern(jtfs[0].getText(), jtfs[1].getText(), jtfs[2].getText(), 
							muskelgruppen.getSelectedItem().toString(), parent));
				}
				else {
					setMeldung("Füllen sie alle Felder aus!");
				}
			}
			if(e.getSource() == btnUebungSpeichern) {
				if(check()) {
					setMeldung(uc.speichernUebung(jtfs[0].getText(), jtfs[1].getText(), jtfs[2].getText(), 
							muskelgruppen.getSelectedItem().toString(), parent));
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
