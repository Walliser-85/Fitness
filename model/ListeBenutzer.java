package model;

import java.util.ArrayList;

import controllerReaderWriter.BenutzerLesen;
import controllerReaderWriter.BenutzerSchreiben;

public class ListeBenutzer {
private ArrayList<Benutzer> users;
	
	public ListeBenutzer() {
		users = new ArrayList<Benutzer>();
	}
	
	public int size() {
		return users.size();
	}
	
	public ArrayList<Benutzer> getUsers(){
		return users;
	}
	
	public Benutzer getBenutzer(int index){
		return users.get(index);
	}
	
	public Benutzer getBenutzer(String benutzername){
		for (Benutzer benutzer : users) {
			if (benutzer.getBenutzername().equals(benutzername)) {
				return benutzer;
			}
		}
		return null;
	}
	
	public boolean addBenutzer(Benutzer newBenutzer) {
		if(!checkBenutzer(newBenutzer)) {
			users.add(newBenutzer);
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean loadFromFile() {
		try
		{
			BenutzerLesen bl = new BenutzerLesen();
			users = bl.getBenutzer();
			return true;
		}
		catch (Exception e){
			return false;
		}
	}

	public boolean saveList() {
		BenutzerSchreiben bs = new BenutzerSchreiben();
		if (bs.writeList(this)) {
			bs.close();
			return true;
		}
		else {
			bs.close();
			return false;
		}
	}
	
	private boolean checkBenutzer(Benutzer benutzer) {
		boolean isVorhanden = false;
		for (int i = 0; i < users.size(); i++) {
			if (benutzer.getBenutzername().equals(users.get(i).getBenutzername())) {
				isVorhanden = true;
			}
		}
		return isVorhanden;
	}
	
	public boolean delete(String benutzername) {
		for (int i = 0; i < size();i++) {
			if (benutzername.equals(getBenutzer(i).getBenutzername())) {
				users.remove(i);
				return true;
			}
		}
		return false;
	}
}
