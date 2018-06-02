package controllerReaderWriter;

import java.util.ArrayList;

import model.Uebung;

public class UebungLesen extends UniversalReader{
	private ArrayList<Uebung> uebungen = new ArrayList<Uebung>();
	public UebungLesen() {
		this.filename = "Uebungen.txt";
	}
	
	/**
	 * @return the muskelgruppen
	 */
	public ArrayList<Uebung> getUebungen() {
		kompletteListeLaden();
		return uebungen;
	}

	@Override
	void addToListe(String zeile) {
		String [] data = zeile.split("#");
		try {
			Uebung uebung = new Uebung(Integer.parseInt(data[0]), data[1], data[2], Uebung.parseMuskelgruppe(data[3]));
			uebungen.add(uebung);
		}catch(ArrayIndexOutOfBoundsException e) {
			System.err.println("Fehlerhaftes Datenformat");
		}
	}

	@Override
	void kompletteListeLaden() {
		openFile();
		uebungen = new ArrayList<Uebung>();
		read();
		closeFile();
	}
}
