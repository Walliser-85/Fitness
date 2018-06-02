package model;

public class Benutzer {
	private int id;
	private String vorname;
	private String nachname;
	private String benutzername;
	private String passwort;
	private String berechtigung;
	
	public Benutzer(int id, String vorname, String nachname, String benutzername, String passwort, String berechtigung) {
		this.id = id;
		this.vorname = vorname; 
		this.nachname = nachname;
		this.benutzername = benutzername;
		this.passwort = passwort;
		this.berechtigung = berechtigung;
	}
	
	public Benutzer() {
		
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the vorname
	 */
	public String getVorname() {
		return vorname;
	}

	/**
	 * @param vorname the vorname to set
	 */
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	/**
	 * @return the nachname
	 */
	public String getNachname() {
		return nachname;
	}

	/**
	 * @param nachname the nachname to set
	 */
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	/**
	 * @return the benutzername
	 */
	public String getBenutzername() {
		return benutzername;
	}

	/**
	 * @param benutzername the benutzername to set
	 */
	public void setBenutzername(String benutzername) {
		this.benutzername = benutzername;
	}

	/**
	 * @return the passwort
	 */
	public String getPasswort() {
		return passwort;
	}

	/**
	 * @param passwort the passwort to set
	 */
	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	/**
	 * @return the berechtigung
	 */
	public String getBerechtigung() {
		return berechtigung;
	}

	/**
	 * @param berechtigung the berechtigung to set
	 */
	public void setBerechtigung(String berechtigung) {
		this.berechtigung = berechtigung;
	}
	
	public String toString() {
		return id + "#" + vorname + "#" + nachname + "#" + benutzername + "#"
				+ passwort + "#" + berechtigung + "\n";
	}
}
