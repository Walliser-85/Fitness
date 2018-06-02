package controllerReaderWriter;

import java.util.ArrayList;

import model.Benutzer;

public class BenutzerLesen extends UniversalReader{
	private ArrayList<Benutzer> users = new ArrayList<Benutzer>();
	public BenutzerLesen() {
		this.filename = "Benutzer.txt";
	}
	
	/**
	 * @return the muskelgruppen
	 */
	public ArrayList<Benutzer> getBenutzer() {
		kompletteListeLaden();
		return users;
	}

	@Override
	void addToListe(String zeile) {
		String [] data = zeile.split("#");
		try {
			Benutzer benutzer = new Benutzer(Integer.parseInt(data[0]), data[1], data[2], data[3], data[4], data[5]);
			users.add(benutzer);
		}catch(ArrayIndexOutOfBoundsException e) {
			System.err.println("Fehlerhaftes Datenformat");
		}
	}

	@Override
	void kompletteListeLaden() {
		openFile();
		users = new ArrayList<Benutzer>();
		read();
		closeFile();
	}
}
