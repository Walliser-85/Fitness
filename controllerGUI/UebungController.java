package controllerGUI;

import model.ListeMuskelgruppe;
import model.ListeUebung;
import model.Uebung;
import viewGUI.FitnessManager;
import viewGUI.UebungErstellen;

public class UebungController extends ControllerVorlage{
private ListeUebung lu;
	
	public UebungController() {
		lu = new ListeUebung();
		lu.loadFromFile();
	}
	
	public int getCountUebungen() {
		return lu.size();
	}
	
	public String[] getUebungenListeCb() {
		String[] temp = new String[lu.size()];
		for(int i=0; i<lu.size(); i++) {
			temp[i]=lu.getUebung(i).getName();
		}
		return temp;
	}
	
	public Object[][] getUebungenListe(){
		Object[][] temp = new Object[lu.size()][4];
		for(int i=0; i<lu.size();i++) {
			temp[i][0] = lu.getUebung(i).getId();
			temp[i][1] = lu.getUebung(i).getName();
			temp[i][2] = lu.getUebung(i).getBeschreibung();
			temp[i][3] = lu.getUebung(i).getMuskel().getName();
		}
		return temp;
	}
	
	public Uebung getUebung(int index) {
		return lu.getUebung(index);
	}
	
	public String speichern(String id, String name, String beschreibung, String muskelname, FitnessManager frame) {
		if(!checkStandartName(name)) {
			return "Dieser Name ist nicht gültig, elaubt sind nur Buchstaben und Leerzeichen";
		}
		Uebung uebung = new Uebung(Integer.parseInt(id), name, beschreibung, Uebung.parseMuskelgruppe(muskelname));
		if(!lu.addUebung(uebung)) {
			return "Diese Uebung existiert bereits!";
		}
		if(lu.saveList()) {
			frame.aktualisierenUebung();
			return "Erfolgreich gespeichert!";
		}
		else {
			return "Fehler beim speichern der Änderung";
		}
	}
	
	public String speichernUebung(String id, String name, String beschreibung, String muskelname, FitnessManager frame) {
		if(!checkStandartName(name)) {
			return "Dieser Name ist nicht gültig, elaubt sind nur Buchstaben und Leerzeichen";
		}
		Uebung uebung = lu.getUebung(Integer.parseInt(id)-1);
		uebung.setBeschreibung(beschreibung);
		uebung.setName(name);
		uebung.setMuskel(Uebung.parseMuskelgruppe(muskelname));
		if(lu.saveList()) {
			frame.aktualisierenUebung();
			return "Erfolgreich gespeichert!";
		}
		else {
			return "Fehler beim speichern der Änderung";
		}
	}
}
