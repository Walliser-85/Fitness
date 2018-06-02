package model;

import java.util.Date;

public class Trainingsplan {
	private int id;
	private String titel;
	private String beschreibung;
	private String datum;
	private Kunde kunde;
	private ListeUebungTrainingsplan uebungen;
	
	public Trainingsplan(int id, String titel, String beschreibung, String datum, Kunde kunde, ListeUebungTrainingsplan uebungen) {
		this.id = id;
		this.titel = titel;
		this.beschreibung = beschreibung;
		this.datum = datum;
		this.kunde = kunde;
		this.uebungen = uebungen;
	}
	
	public Trainingsplan() {
		
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param titel the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * @return the titel
	 */
	public String getTitel() {
		return titel;
	}

	/**
	 * @param titel the titel to set
	 */
	public void setTitel(String titel) {
		this.titel = titel;
	}

	/**
	 * @return the beschreibung
	 */
	public String getBeschreibung() {
		return beschreibung;
	}

	/**
	 * @param beschreibung the beschreibung to set
	 */
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	/**
	 * @return the datum
	 */
	public String getDatum() {
		return datum;
	}

	/**
	 * @param datum the datum to set
	 */
	public void setDatum(String datum) {
		this.datum = datum;
	}

	/**
	 * @return the kunde
	 */
	public Kunde getKunde() {
		return kunde;
	}

	/**
	 * @param kunde the kunde to set
	 */
	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}

	/**
	 * @return the uebungen
	 */
	public ListeUebungTrainingsplan getUebungen() {
		return uebungen;
	}

	/**
	 * @param uebungen the uebungen to set
	 */
	public void setUebungen(ListeUebungTrainingsplan uebungen) {
		this.uebungen = uebungen;
	}
	
	public String toString() {
		String uebungenStr  ="";
		for(int i = 0; i<uebungen.size(); i++) {
			uebungenStr += id + "#" + uebungen.getUebung(i).toString();
		}
		System.out.println(uebungenStr);
		System.out.println(titel);
		System.out.println(beschreibung);
		System.out.println(datum);
		System.out.println(kunde.getVorname());
		System.out.println(kunde.getNachname());
		
		return id + "#" +titel +"#" + beschreibung + "#" + datum + "#" + kunde.getVorname() + " " + kunde.getNachname() + "\n" + uebungenStr;
	}
	
}
