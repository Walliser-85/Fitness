package controllerReaderWriter;

import java.io.File;
import java.io.IOException;

import model.Uebung;
import model.ListeUebung;

public class UebungSchreiben extends UniversalWriter{
	public UebungSchreiben() {
		super(new File("Uebungen.txt"));
	}
	
	public void write(Uebung uebung) {
		try {
			String uebungTXT = uebung.toString();
			out.write(uebungTXT);
		} catch (IOException e) {
			System.out.println("Fehler beim Schreiben.");
		}
	}
	
	public boolean writeList(ListeUebung uebung) {
		try {
			for(int i=0; i<uebung.size(); i++) {
				write(uebung.getUebung(i));
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
