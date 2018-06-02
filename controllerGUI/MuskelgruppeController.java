package controllerGUI;

import model.Muskelgruppe;
import model.Uebung;
import viewGUI.FitnessManager;
import model.ListeMuskelgruppe;

public class MuskelgruppeController extends ControllerVorlage{
	private ListeMuskelgruppe lm;
	
	public MuskelgruppeController() {
		lm = new ListeMuskelgruppe();
		lm.loadFromFile();
	}
	
	public int getCountMuskelgruppen() {
		return lm.size();
	}
	
	public String[] getMuskelgruppenListeCb() {
		String[] temp = new String[lm.size()];
		for(int i=0; i<lm.size(); i++) {
			temp[i]=lm.getMuskelgruppe(i).getName();
		}
		return temp;
	}
	
	public int getIdFromMuskelgruppe(String muskelname) {
		Muskelgruppe m = lm.getMuskelgruppe(muskelname);
		return m.getId();
	}
	
	public Object[][] getMuskelgruppenListe(){
		Object[][] temp = new Object[lm.size()][3];
		for(int i=0; i<lm.size();i++) {
			temp[i][0] = lm.getMuskelgruppe(i).getId();
			temp[i][1] = lm.getMuskelgruppe(i).getName();
			temp[i][2] = lm.getMuskelgruppe(i).getBeschreibung();
		}
		return temp;
	}
	
	public Muskelgruppe getMuskelgruppe(int index) {
		return lm.getMuskelgruppe(index);
	}
	
	public String speichern(String id, String name, String beschreibung, FitnessManager frame) {
		if(!checkStandartName(name)) {
			return "Dieser Name ist nicht gültig, elaubt sind nur Buchstaben und Leerzeichen";
		}
		Muskelgruppe muskel = new Muskelgruppe(Integer.parseInt(id), name, beschreibung);
		if(!lm.addMuskelgruppe(muskel)) {
			return "Diese Muskelgruppe existiert bereits!";
		}
		if(lm.saveList()) {
			frame.aktualisierenMuskelgruppe();
			return "Erfolgreich gespeichert!";
		}
		else {
			return "Fehler beim speichern der Änderung";
		}
	}
	
	public String speichernMuskelgruppe(String id, String name, String beschreibung, FitnessManager frame) {
		if(!checkStandartName(name)) {
			return "Dieser Name ist nicht gültig, elaubt sind nur Buchstaben und Leerzeichen";
		}
		Muskelgruppe muskel = lm.getMuskelgruppe(Integer.parseInt(id)-1);
		muskel.setBeschreibung(beschreibung);
		muskel.setName(name);
		if(lm.saveList()) {
			frame.aktualisierenMuskelgruppe();
			return "Erfolgreich gespeichert!";
		}
		else {
			return "Fehler beim speichern der Änderung";
		}
	}
}
