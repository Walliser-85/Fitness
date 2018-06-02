package controllerReaderWriter;

import java.util.ArrayList;

import model.Kunde;

public class KundeLesen extends UniversalReader{
	private ArrayList<Kunde> kunden = new ArrayList<Kunde>();
	public KundeLesen() {
		this.filename = "Kunden.txt";
	}
	
	/**
	 * @return the muskelgruppen
	 */
	public ArrayList<Kunde> getKunde() {
		kompletteListeLaden();
		return kunden;
	}

	@Override
	void addToListe(String zeile) {
		String [] data = zeile.split("#");
		try {
			Kunde kunde = new Kunde(Integer.parseInt(data[0]), data[1], data[2], data[3], Integer.parseInt(data[4]), data[5], data[6]);
			kunden.add(kunde);
		}catch(ArrayIndexOutOfBoundsException e) {
			System.err.println("Fehlerhaftes Datenformat");
		}
	}

	@Override
	void kompletteListeLaden() {
		openFile();
		kunden = new ArrayList<Kunde>();
		read();
		closeFile();
	}
}
