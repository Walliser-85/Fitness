package controllerGUI;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.Kunde;
import model.ListeTrainingsplan;
import model.ListeUebung;
import model.ListeUebungTrainingsplan;
import model.Trainingsplan;
import model.Uebung;
import model.UebungTrainingsplan;
import viewGUI.FitnessManager;

public class TrainingsplanController extends ControllerVorlage{
	private ListeTrainingsplan lt;
	private ListeUebungTrainingsplan lut;
	private ListeUebung lu;

	public TrainingsplanController() {
		lt = new ListeTrainingsplan();
		lt.loadFromFile();
		lut = new ListeUebungTrainingsplan();
		lu = new ListeUebung();
		lu.loadFromFile();
	}

	public int getCountTrainingsplaene() {
		return lt.size();
	}

	public int getCountUebungen() {
		return lut.size();
	}

	public Boolean checkUebungenExists() {
		if(lut.size() == 0) {
			return false;
		}
		else {
			return true;
		}
	}

	public Object[][] getTrainingsplanListe(){
		Object[][] temp = new Object[lt.size()][6];
		for(int i=0; i<lt.size();i++) {
			System.out.println(lt.getTrainingsplan(i).getId());
			System.out.println(lt.getTrainingsplan(i).getTitel());
			System.out.println(lt.getTrainingsplan(i).getBeschreibung());
			temp[i][0] = lt.getTrainingsplan(i).getId();
			temp[i][1] = lt.getTrainingsplan(i).getTitel();
			temp[i][2] = lt.getTrainingsplan(i).getBeschreibung();
			temp[i][3] = lt.getTrainingsplan(i).getDatum();
			temp[i][4] = lt.getTrainingsplan(i).getKunde().getVorname() + " " + lt.getTrainingsplan(i).getKunde().getNachname();
			temp[i][5] = lt.getTrainingsplan(i).getUebungen().size();
		}
		return temp;
	}

	public Object[][] getUebungsListe(){
		Object[][] temp = new Object[lut.size()][6];
		for(int i=0; i<lut.size(); i++) {
			temp[i][0] = lut.getUebung(i).getId();
			temp[i][1] = lut.getUebung(i).getName();
			temp[i][2] = lut.getUebung(i).getBeschreibung();
			temp[i][3] = lut.getUebung(i).getMuskel().getName();
			temp[i][4] = lut.getUebung(i).getWiederholungen();
			temp[i][5] = lut.getUebung(i).getSaetze();
		}
		return temp;
	}

	public String addUebung(String uebungsname, String wiederholungen, String saetze) {
		Uebung uebung = lu.getUebung(uebungsname);
		UebungTrainingsplan ut;
		try {
			ut = new UebungTrainingsplan(uebung.getId(), uebung.getName(), uebung.getBeschreibung(), 
					uebung.getMuskel(), Integer.parseInt(wiederholungen), Integer.parseInt(saetze));
			if(!lut.addUebung(ut)) {
				return "Diese Übung ist schon dem Trainingsplan vorhanden";
			}
			else {
				return "Übung erfolgreich hinzugefügt";
			}
		} catch (NumberFormatException e) {
			return "Im Feld Wiederholungen und Sätze können nur Zahlen eingeben werden!";
		}
	}

	public void deleteUebung(int index) {
		lut.delete(index);
	}
	
	public Boolean deleteTrainingsplan(int index) {
		lt.delete(index);
		if(lt.saveList()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public String speichern(String id, String titel, String beschreibung, String datum, String kunde, FitnessManager frame){
		if(!checkStandartName(titel)) {
			return "Dieser Titel ist nicht gültig, elaubt sind nur Buchstaben und Leerzeichen";
		}
		Trainingsplan trainingsplan = new Trainingsplan(Integer.parseInt(id), titel, beschreibung, datum, Kunde.parseKunde(kunde), lut);
		lt.addTrainingsplan(trainingsplan);
		if(lt.saveList()) {
			frame.aktualisierenTrainingsplan();
			return "Erfolgreich gespeichert!";
		}
		else {
			return "Fehler beim speichern der Änderung";
		}
	}
}
