package controllerReaderWriter;

import java.io.File;
import java.io.IOException;

import model.ListeMuskelgruppe;
import model.Muskelgruppe;

public class MuskelgruppeSchreiben extends UniversalWriter{
	public MuskelgruppeSchreiben() {
		super(new File("Muskelgruppen.txt"));
	}
	
	public void write(Muskelgruppe muskel) {
		try {
			String muskelTXT = muskel.toString();
			out.write(muskelTXT);
		} catch (IOException e) {
			System.out.println("Fehler beim Schreiben.");
		}
	}
	
	public boolean writeList(ListeMuskelgruppe muskelgruppen) {
		try {
			for(int i=0; i<muskelgruppen.size(); i++) {
				write(muskelgruppen.getMuskelgruppe(i));
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
