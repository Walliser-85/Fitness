package controllerGUI;

import model.ListeKunde;
import viewGUI.FitnessManager;
import model.Kunde;

public class KundeController extends ControllerVorlage{
private ListeKunde lk;
	
	public KundeController() {
		lk = new ListeKunde();
		lk.loadFromFile();
	}
	
	public int getCountKunde() {
		return lk.size();
	}
	
	public String[] getKundeListeCb() {
		String[] temp = new String[lk.size()];
		for(int i=0; i<lk.size(); i++) {
			temp[i]=lk.getKunde(i).getVorname() + " " + lk.getKunde(i).getNachname();
		}
		return temp;
	}
	
	public Object[][] getKundeListe(){
		Object[][] temp = new Object[lk.size()][7];
		for(int i=0; i<lk.size();i++) {	
			//if(lk.getKunde(i).getUser().equals(user)) {
				temp[i][0] = lk.getKunde(i).getId();
				temp[i][1] = lk.getKunde(i).getVorname();
				temp[i][2] = lk.getKunde(i).getNachname();
				temp[i][3] = lk.getKunde(i).getAdresse();
				temp[i][4] = lk.getKunde(i).getPlz();
				temp[i][5] = lk.getKunde(i).getOrt();
				temp[i][6] = lk.getKunde(i).getEmail();
			//}
		}
		return temp;
	}
	
	public String speichern(String id, String vorname, String nachname, String adresse, String plz, String ort, String email, FitnessManager frame) {
		Kunde kunde = new Kunde(Integer.parseInt(id), vorname, nachname, adresse, Integer.parseInt(plz), ort, email);
		if(!checkStandartName(vorname)) {
			return "Dieser Vorname ist nicht gültig, elaubt sind nur Buchstaben und Leerzeichen";
		}
		if(!checkStandartName(nachname)) {
			return "Dieser Nachname ist nicht gültig, elaubt sind nur Buchstaben und Leerzeichen";
		}
		if(!checkStandartName(ort)) {
			return "Dieser Ort ist nicht gültig, elaubt sind nur Buchstaben und Leerzeichen";
		}
		/*if(!checkEmail(email)) {
			return "Üngültige Email Adrese";
		}*/
		if(!lk.addKunde(kunde)) {
			return "Diese Muskelgruppe existiert bereits!";
		}
		if(lk.saveList()) {
			frame.aktualisierenKunde();
			return "Erfolgreich gespeichert!";
		}
		else {
			return "Fehler beim speichern der Änderung";
		}
	}
}
