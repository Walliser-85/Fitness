package viewGUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controllerGUI.*;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import net.miginfocom.swing.MigLayout;
import viewKomponenten.EinstellungenView;

import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

public class TrainingsplanErstellen extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private String labelText[] = {"Id", "Titel", "Beschreibung", "Datum", "Kunde"};
	private JTextField jtfs[];
	private JLabel lbs[];
	private JLabel meldung;
	
	private TrainingsplanController tc;
	private KundeController kc;
	private UebungController uc;
	private String[] columnNamesUebung = {"Übungs-ID", "Name", "Beschreibung", "Muskel", "Wiederholungen", "Sätze"};
	private JTextField txtWiederholungen;
	
	private JButton btnAdd, btnRemove, okButton, cancelButton;
	private JTable tbl_Uebungen;
	private JComboBox cbUebungen, cbKunden;
	private JTextField txtSaetze;

	private int selectedRow = -1;
	private FitnessManager parent;

	/**
	 * Create the dialog.
	 */
	public TrainingsplanErstellen(FitnessManager parent, TrainingsplanController tc) {
		super(parent, "Trainingsplan Erstellen", true);
		this.tc = tc;
		this.parent = parent;
		
		kc = new KundeController();
		uc = new UebungController();
		
		Date date = new Date();
	    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); 
	    String datum = sdf.format(date);
	    
	    setResizable(false);
		setBounds(100, 100, 990, 970);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		
		JLabel lblTraininsplanErstellen = new JLabel("Traininsplan Erstellen");
		lblTraininsplanErstellen.setBorder(new EmptyBorder(10,10,10,0));
		lblTraininsplanErstellen.setOpaque(true);
		lblTraininsplanErstellen.setBackground(Color.GRAY);
		lblTraininsplanErstellen.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPanel.add(lblTraininsplanErstellen, BorderLayout.NORTH);
		
		JPanel pnlDashErstellen = new JPanel();
		pnlDashErstellen.setBorder(new EmptyBorder(10,10,10,10));
		contentPanel.add(pnlDashErstellen, BorderLayout.CENTER);
		pnlDashErstellen.setLayout(new GridLayout(1, 0, 0, 0));
		pnlDashErstellen.setLayout(new GridLayout(0, 2, 0, 10));
			
		lbs = new JLabel[labelText.length];
		for (int i = 0; i < lbs.length; i++) {
			lbs[i] = new JLabel(labelText[i]+" : ");
			lbs[i].setFont(EinstellungenView.standartFont);
		}
		
		jtfs = new JTextField[labelText.length-1];
		for (int i = 0; i < jtfs.length; i++) {
			if(i == 0) {
				jtfs[i] = new JTextField(Integer.toString(tc.getCountTrainingsplaene()+1));
			}
			else {
				jtfs[i] = new JTextField();
			}
			jtfs[i].setFont(EinstellungenView.standartFont);
		}
		
		jtfs[0].setEnabled(false);
		jtfs[3].setEnabled(false);
		
		jtfs[3].setText(datum);
		
		meldung = new JLabel("");
		meldung.setFont(EinstellungenView.standartFont);
		
		cbKunden = new JComboBox(kc.getKundeListeCb());
		cbKunden.setFont(EinstellungenView.standartFont);
		
		for(int i=0; i<lbs.length-1;i++) {
			pnlDashErstellen.add(lbs[i]);
			pnlDashErstellen.add(jtfs[i]);
		}
		
		pnlDashErstellen.add(lbs[4]);
		pnlDashErstellen.add(cbKunden);
		
		pnlDashErstellen.add(meldung);
		pnlDashErstellen.add(new JLabel(""));
		
		
		JPanel pnlUebungen = new JPanel();
		contentPanel.add(pnlUebungen, BorderLayout.SOUTH);
		pnlUebungen.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlTopUebungen = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pnlTopUebungen.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		flowLayout.setHgap(20);
		flowLayout.setVgap(10);
		pnlUebungen.add(pnlTopUebungen, BorderLayout.NORTH);
		
		JLabel lblUebung = new JLabel("\u00DCbung:");
		lblUebung.setFont(EinstellungenView.standartFont);
		pnlTopUebungen.add(lblUebung);
		
		cbUebungen = new JComboBox(uc.getUebungenListeCb());
		cbUebungen.setFont(EinstellungenView.standartFont);
		pnlTopUebungen.add(cbUebungen);
		
		JLabel lblWiederholungen = new JLabel("Wiederholungen:");
		lblWiederholungen.setFont(EinstellungenView.standartFont);
		pnlTopUebungen.add(lblWiederholungen);
		
		txtWiederholungen = new JTextField();
		txtWiederholungen.setFont(EinstellungenView.standartFont);
		pnlTopUebungen.add(txtWiederholungen);
		txtWiederholungen.setColumns(2);
		
		MeinActionListener handler = new MeinActionListener();
		
		JLabel lblSaetze = new JLabel("S\u00E4tze:");
		lblSaetze.setFont(EinstellungenView.standartFont);
		pnlTopUebungen.add(lblSaetze);
		
		txtSaetze = new JTextField();
		txtSaetze.setFont(EinstellungenView.standartFont);
		pnlTopUebungen.add(txtSaetze);
		txtSaetze.setColumns(2);
		
		btnAdd = new JButton("Add");
		btnAdd.setFont(EinstellungenView.buttonFont);
		pnlTopUebungen.add(btnAdd);
		
		btnRemove = new JButton("Remove");
		btnRemove.setFont(EinstellungenView.buttonFont);
		pnlTopUebungen.add(btnRemove);
		
		btnAdd.addActionListener(handler);
		btnRemove.addActionListener(handler);
		
		JPanel pnlContentUebungen = new JPanel();
		pnlUebungen.add(pnlContentUebungen, BorderLayout.CENTER);
		pnlContentUebungen.setBorder(new EmptyBorder(10,10,10,10));
		
		tbl_Uebungen = new JTable(tc.getUebungsListe(),columnNamesUebung);
		pnlContentUebungen.setLayout(new GridLayout(0, 1, 0, 0));
		tbl_Uebungen.setBackground(Color.WHITE);
		JScrollPane scrollpaneUebung = new JScrollPane(tbl_Uebungen);
		pnlContentUebungen.add(scrollpaneUebung);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		okButton = new JButton("Trainingsplan Erstellen");
		okButton.setFont(EinstellungenView.buttonFont);
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		
		cancelButton = new JButton("Cancel");
		cancelButton.setFont(EinstellungenView.buttonFont);
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
		
		okButton.addActionListener(handler);
		cancelButton.addActionListener(handler);
		
		tbl_Uebungen.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		        selectedRow = tbl_Uebungen.rowAtPoint(evt.getPoint());
		    }
		});
			
	}
	
	private void setMeldung(String meldungStr) {
		meldung.setText("<html>"+meldungStr+"</html>");
	}
	
	private void borderNull() {
		for(int i=0; i< jtfs.length; i++) {
			jtfs[i].setBorder(null);
		}
	}
	
	private void borderNullUebung() {
		txtWiederholungen.setBorder(null);
		txtSaetze.setBorder(null);
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
	
	private boolean checkUebung() {
		borderNullUebung();
		boolean ausgefuellt = true;
		if(txtWiederholungen.getText().equals("")) {
			ausgefuellt = false;
			txtWiederholungen.setBorder(new LineBorder(Color.RED));
		}
		if(txtSaetze.getText().equals("")) {
			ausgefuellt = false;
			txtSaetze.setBorder(new LineBorder(Color.RED));
		}
		return ausgefuellt;
	}
	
	private class MeinActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnAdd) {
				System.out.println("ADD");
				if(checkUebung()) {
					setMeldung(tc.addUebung(cbUebungen.getSelectedItem().toString(), txtWiederholungen.getText(), txtSaetze.getText()));
					tbl_Uebungen.setModel(new DefaultTableModel(tc.getUebungsListe(),columnNamesUebung));
				}
				else {
					setMeldung("Füllen sie alle Felder aus um eine Übung hinzuzufügen!");
				}
			}
			if(e.getSource() == btnRemove) {
				System.out.println("Uebung löschen");
				if(selectedRow == -1) {
					System.out.println("Uebung zum löschen anklicken");
				}
				else {
					tc.deleteUebung(selectedRow);
					tbl_Uebungen.setModel(new DefaultTableModel(tc.getUebungsListe(),columnNamesUebung));
				}
			}
			if(e.getSource() == okButton) {
				System.out.println("Trainingsplan ertellen");
				if(check() && tc.checkUebungenExists()) {
					setMeldung(tc.speichern(jtfs[0].getText(), jtfs[1].getText(), jtfs[2].getText(), 
							jtfs[3].getText(), cbKunden.getSelectedItem().toString(), parent));
				}
				else {
					setMeldung("Füllen sie alle Pflichtfelder aus!");
				}
			}
			if(e.getSource() == cancelButton) {
				dispose();
			}
		}
		
	}
}
