package controllerReaderWriter;

import java.io.File;
import java.io.IOException;

import model.ListeKunde;
import model.Kunde;;

public class KundeSchreiben extends UniversalWriter{
	public KundeSchreiben() {
		super(new File("Kunden.txt"));
	}
	
	public void write(Kunde kunde) {
		try {
			String kundeTXT = kunde.toString();
			out.write(kundeTXT);
		} catch (IOException e) {
			System.out.println("Fehler beim Schreiben.");
		}
	}
	
	public boolean writeList(ListeKunde kunden) {
		try {
			for(int i=0; i<kunden.size(); i++) {
				write(kunden.getKunde(i));
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
