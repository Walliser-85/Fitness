package model;

public class UebungTrainingsplan extends Uebung{
	private int wiederholungen;
	private int pos;
	private int saetze;
	
	public UebungTrainingsplan(int id, String name, String beschreibung, Muskelgruppe muskel, int wiederholungen, int saetze) {
		this.id = id;
		this.name = name;
		this.beschreibung = beschreibung;
		this.muskel = muskel;
		this.wiederholungen = wiederholungen;
		this.saetze = saetze;
	}

	
	/**
	 * @return the wiederholungen
	 */
	public int getWiederholungen() {
		return wiederholungen;
	}

	/**
	 * @param wiederholungen the wiederholungen to set
	 */
	public void setWiederholungen(int wiederholungen) {
		this.wiederholungen = wiederholungen;
	}

	/**
	 * @return the saetze
	 */
	public int getSaetze() {
		return saetze;
	}

	/**
	 * @param saetze the saetze to set
	 */
	public void setSaetze(int saetze) {
		this.saetze = saetze;
	}

	public String toString() {
		return id +"#" + name + "#" + beschreibung + "#" + muskel.getName() + "#" + wiederholungen + "#" + saetze + "#" + "\n";
	}
}
