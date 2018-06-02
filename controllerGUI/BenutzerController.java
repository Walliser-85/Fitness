package controllerGUI;

import model.Benutzer;
import model.ListeBenutzer;
import viewGUI.FitnessManager;

public class BenutzerController extends ControllerVorlage{
private ListeBenutzer lb;
	
	public BenutzerController() {
		lb = new ListeBenutzer();
		lb.loadFromFile();
	}
	
	public int getCountBenutzer() {
		return lb.size();
	}
	
	public Object[][] getBenutzerListe(){
		Object[][] temp = new Object[lb.size()][6];
		for(int i=0; i<lb.size();i++) {			
			temp[i][0] = lb.getBenutzer(i).getId();
			temp[i][1] = lb.getBenutzer(i).getVorname();
			temp[i][2] = lb.getBenutzer(i).getNachname();
			temp[i][3] = lb.getBenutzer(i).getBenutzername();
			temp[i][4] = lb.getBenutzer(i).getPasswort();
			temp[i][5] = lb.getBenutzer(i).getBerechtigung();
		}
		return temp;
	}
	
	public String speichern(String id, String vorname, String nachname, String benutzername, String passwort, String berechtigung, FitnessManager frame) {
		if(!checkStandartName(vorname)) {
			return "Dieser Vorname ist nicht gültig, elaubt sind nur Buchstaben und Leerzeichen";
		}
		if(!checkStandartName(nachname)) {
			return "Dieser Nachname ist nicht gültig, elaubt sind nur Buchstaben und Leerzeichen";
		}
		Benutzer benutzer = new Benutzer(Integer.parseInt(id), vorname, nachname, benutzername, passwort, berechtigung);
		if(!lb.addBenutzer(benutzer)) {
			return "Dieser Benutzername existiert bereits!";
		}
		if(lb.saveList()) {
			frame.aktualisierenBenutzer();
			return "Neuer Benutzer erfolgreich erstellt!";
		}
		else {
			return "Fehler beim erstellen des neuen Benutzers";
		}
	}
}
