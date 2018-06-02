package model;

import java.util.ArrayList;

import controllerReaderWriter.UebungLesen;
import controllerReaderWriter.UebungSchreiben;

public class ListeUebung {
private ArrayList<Uebung> uebungen;
	
	public ListeUebung() {
		uebungen = new ArrayList<Uebung>();
	}
	
	public int size() {
		return uebungen.size();
	}
	
	public ArrayList<Uebung> getUebungen(){
		return uebungen;
	}
	
	public Uebung getUebung(int index){
		return uebungen.get(index);
	}
	
	public Uebung getUebung(String uebungsname){
		for (Uebung uebung : uebungen) {
			if (uebung.getName().equals(uebungsname)) {
				return uebung;
			}
		}
		return null;
	}
	
	public boolean addUebung(Uebung newUebung) {
		if(!checkUebung(newUebung)) {
			uebungen.add(newUebung);
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean loadFromFile() {
		try
		{
			UebungLesen ul = new UebungLesen();
			uebungen = ul.getUebungen();
			ul.closeFile();
			return true;
		}
		catch (Exception e){
			return false;
		}
	}

	public boolean saveList() {
		UebungSchreiben us = new UebungSchreiben();
		if (us.writeList(this)) {
			us.close();
			return true;
		}
		else {
			us.close();
			return false;
		}
	}
	
	private boolean checkUebung(Uebung uebung) {
		boolean isVorhanden = false;
		for (int i = 0; i < uebungen.size(); i++) {
			if (uebung.getName().equals(uebungen.get(i).getName())) {
				isVorhanden = true;
			}
		}
		return isVorhanden;
	}
	
	public boolean delete(String mgString) {
		for (int i = 0; i < size();i++) {
			if (mgString.equals(getUebung(i).getName())) {
				uebungen.remove(i);
				return true;
			}
		}
		return false;
	}
}
