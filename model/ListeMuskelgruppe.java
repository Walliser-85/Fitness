package model;

import java.util.ArrayList;

import controllerReaderWriter.MuskelgruppeLesen;
import controllerReaderWriter.MuskelgruppeSchreiben;

public class ListeMuskelgruppe {
	private ArrayList<Muskelgruppe> muskelgruppen;
	
	public ListeMuskelgruppe() {
		muskelgruppen = new ArrayList<Muskelgruppe>();
	}
	
	public int size() {
		return muskelgruppen.size();
	}
	
	public ArrayList<Muskelgruppe> getMuskelgruppen(){
		return muskelgruppen;
	}
	
	public Muskelgruppe getMuskelgruppe(int index){
		return muskelgruppen.get(index);
	}
	
	public Muskelgruppe getMuskelgruppe(String muskelname){
		for (Muskelgruppe muskel : muskelgruppen) {
			if (muskel.getName().equals(muskelname)) {
				return muskel;
			}
		}
		return null;
	}
	
	public boolean addMuskelgruppe(Muskelgruppe newMuskelgruppe) {
		if(!checkMuskelgruppe(newMuskelgruppe)) {
			muskelgruppen.add(newMuskelgruppe);
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean loadFromFile() {
		try
		{
			MuskelgruppeLesen ml = new MuskelgruppeLesen();
			muskelgruppen = ml.getMuskelgruppen();
			ml.closeFile();
			return true;
		}
		catch (Exception e){
			return false;
		}
	}

	public boolean saveList() {
		MuskelgruppeSchreiben ms = new MuskelgruppeSchreiben();
		if (ms.writeList(this)) {
			ms.close();
			return true;
		}
		else {
			ms.close();
			return false;
		}
	}
	
	private boolean checkMuskelgruppe(Muskelgruppe muskelgruppe) {
		boolean isVorhanden = false;
		for (int i = 0; i < muskelgruppen.size(); i++) {
			if (muskelgruppe.getName().equals(muskelgruppen.get(i).getName())) {
				isVorhanden = true;
			}
		}
		return isVorhanden;
	}
	
	public boolean delete(String mgString) {
		for (int i = 0; i < size();i++) {
			if (mgString.equals(getMuskelgruppe(i).getName())) {
				muskelgruppen.remove(i);
				return true;
			}
		}
		return false;
	}
}
