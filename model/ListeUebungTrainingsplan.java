package model;

import java.util.ArrayList;

public class ListeUebungTrainingsplan {
private ArrayList<UebungTrainingsplan> uebungen;
	
	public ListeUebungTrainingsplan() {
		uebungen = new ArrayList<UebungTrainingsplan>();
	}
	
	public int size() {
		return uebungen.size();
	}
	
	public ArrayList<UebungTrainingsplan> getUebungen(){
		return uebungen;
	}
	
	public UebungTrainingsplan getUebung(int index){
		return uebungen.get(index);
	}
	
	public UebungTrainingsplan getUebung(String uebungsname){
		for (UebungTrainingsplan uebung : uebungen) {
			if (uebung.getName().equals(uebungsname)) {
				return uebung;
			}
		}
		return null;
	}
	
	public boolean addUebung(UebungTrainingsplan newUebung) {
		if(!checkUebung(newUebung)) {
			uebungen.add(newUebung);
			return true;
		}
		else {
			return false;
		}
	}
	
	public void addUebungReader(UebungTrainingsplan newUebung) {
		uebungen.add(newUebung);
	}
	
	/*public boolean loadFromFile() {
		try
		{
			UebungLesen ul = new UebungLesen();
			uebungen = ul.getUebungen();
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
	}*/
	
	private boolean checkUebung(UebungTrainingsplan uebung) {
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
	
	public void delete(int i) {
		uebungen.remove(i);
	}
	
	public void deleteAll() {
		System.out.println("UEBUNGEN SIZE " + uebungen.size());
		for(int i=0; i<=uebungen.size();i++) {
			System.out.println("Before " + uebungen.size());
			uebungen.remove(i);
			System.out.println("After " + uebungen.size());
			System.out.println("Index " + i);
		}
	}
}
