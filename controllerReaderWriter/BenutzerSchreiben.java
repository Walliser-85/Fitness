package controllerReaderWriter;

import java.io.File;
import java.io.IOException;

import model.Benutzer;
import model.ListeBenutzer;

public class BenutzerSchreiben extends UniversalWriter{
	public BenutzerSchreiben() {
		super(new File("Benutzer.txt"));
	}
	
	public void write(Benutzer benutzer) {
		try {
			String benutzerTXT = benutzer.toString();
			out.write(benutzerTXT);
		} catch (IOException e) {
			System.out.println("Fehler beim Schreiben.");
		}
	}
	
	public boolean writeList(ListeBenutzer benutzer) {
		try {
			for(int i=0; i<benutzer.size(); i++) {
				write(benutzer.getBenutzer(i));
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
