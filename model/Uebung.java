package model;

public class Uebung {
	protected int id;
	protected String name;
	protected String beschreibung;
	protected Muskelgruppe muskel;
	//private String muskel;
	
	public Uebung(int id, String name, String beschreibung, Muskelgruppe muskel) {
		this.id = id;
		this.name = name;
		this.beschreibung = beschreibung;
		this.muskel = muskel;
	}
	
	/*public Uebung(int id, String name, String beschreibung, String muskel) {
		this.id = id;
		this.name = name;
		this.beschreibung = beschreibung;
		this.muskel = muskel;
	}*/
	
	public Uebung() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public Muskelgruppe getMuskel() {
		return muskel;
	}

	public void setMuskel(Muskelgruppe muskel) {
		this.muskel = muskel;
	}
	
	public static Muskelgruppe parseMuskelgruppe(String muskelname) {
		ListeMuskelgruppe lm = new ListeMuskelgruppe();
		lm.loadFromFile();
		return lm.getMuskelgruppe(muskelname);
	}
	
	public String toString() {
		return id +"#" + name + "#" + beschreibung + "#" + muskel.getName() + "\n";
	}
}
