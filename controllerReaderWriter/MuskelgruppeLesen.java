package controllerReaderWriter;

import model.Muskelgruppe;
import java.util.ArrayList;

public class MuskelgruppeLesen extends UniversalReader{
	private ArrayList<Muskelgruppe> muskelgruppen = new ArrayList<Muskelgruppe>();
	public MuskelgruppeLesen() {
		this.filename = "Muskelgruppen.txt";
	}
	
	/**
	 * @return the muskelgruppen
	 */
	public ArrayList<Muskelgruppe> getMuskelgruppen() {
		kompletteListeLaden();
		return muskelgruppen;
	}

	@Override
	void addToListe(String zeile) {
		String [] data = zeile.split("#");
		try {
			Muskelgruppe muskel = new Muskelgruppe(Integer.parseInt(data[0]), data[1], data[2]);
			muskelgruppen.add(muskel);
		}catch(ArrayIndexOutOfBoundsException e) {
			System.err.println("Fehlerhaftes Datenformat");
		}
	}

	@Override
	void kompletteListeLaden() {
		openFile();
		muskelgruppen = new ArrayList<Muskelgruppe>();
		read();
		closeFile();
	}
}
