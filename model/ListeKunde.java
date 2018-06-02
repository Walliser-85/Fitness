package model;

import java.util.ArrayList;

import controllerReaderWriter.KundeLesen;
import controllerReaderWriter.KundeSchreiben;

public class ListeKunde {
private ArrayList<Kunde> kunden;
	
	public ListeKunde() {
		kunden = new ArrayList<Kunde>();
	}
	
	public int size() {
		return kunden.size();
	}
	
	public ArrayList<Kunde> getKunden(){
		return kunden;
	}
	
	public Kunde getKunde(int index){
		return kunden.get(index);
	}
	
	public Kunde getKunde(String vorname, String nachname){
		for (Kunde kunde : kunden) {
			if (kunde.getVorname().equals(vorname) && kunde.getNachname().equals(nachname)) {
				return kunde;
			}
		}
		return null;
	}
	
	public boolean addKunde(Kunde newKunde) {
		if(!checkKunde(newKunde)) {
			kunden.add(newKunde);
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean loadFromFile() {
		try
		{
			KundeLesen kl = new KundeLesen();
			kunden = kl.getKunde();
			return true;
		}
		catch (Exception e){
			return false;
		}
	}

	public boolean saveList() {
		KundeSchreiben ks = new KundeSchreiben();
		if (ks.writeList(this)) {
			ks.close();
			return true;
		}
		else {
			ks.close();
			return false;
		}
	}
	
	private boolean checkKunde(Kunde kunde) {
		boolean isVorhanden = false;
		for (int i = 0; i < kunden.size(); i++) {
			if (kunde.getVorname().equals(kunden.get(i).getVorname()) && kunde.getNachname().equals(kunden.get(i).getNachname())) {
				isVorhanden = true;
			}
		}
		return isVorhanden;
	}
	
	public boolean delete(String vorname, String nachname) {
		for (int i = 0; i < size();i++) {
			if (vorname.equals(getKunde(i).getVorname()) && nachname.equals(getKunde(i).getNachname())) {
				kunden.remove(i);
				return true;
			}
		}
		return false;
	}
}
