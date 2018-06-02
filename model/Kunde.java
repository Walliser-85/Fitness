package model;

public class Kunde {
	private int id;
	private String vorname;
	private String nachname;
	private String adresse;
	private int plz;
	private String ort;
	private String email;
	private String user;
	
	public Kunde(int id, String vorname, String nachname, String adresse, int plz, String ort, String email) {
		this.id = id;
		this.vorname = vorname;
		this.nachname = nachname;
		this.adresse = adresse;
		this.plz = plz;
		this.ort = ort;
		this.email = email;
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
	 * @return the adresse
	 */
	public String getAdresse() {
		return adresse;
	}

	/**
	 * @param adresse the adresse to set
	 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	/**
	 * @return the plz
	 */
	public int getPlz() {
		return plz;
	}

	/**
	 * @param plz the plz to set
	 */
	public void setPlz(int plz) {
		this.plz = plz;
	}

	/**
	 * @return the ort
	 */
	public String getOrt() {
		return ort;
	}

	/**
	 * @param ort the ort to set
	 */
	public void setOrt(String ort) {
		this.ort = ort;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param email the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}
	
	public static Kunde parseKunde(String kundenName) {
		ListeKunde lk = new ListeKunde();
		lk.loadFromFile();
		String data[] = kundenName.split(" ");
		//Parsen noch vornehmen -- kundenName = Vorname + " " + Nachname
		return lk.getKunde(data[0], data[1]);
	}

	public String toString() {
		return id + "#" + vorname + "#" + nachname + "#" + adresse + "#"
				+ plz + "#" + ort + "#" + email + "#" + "\n";
	}
	
}
