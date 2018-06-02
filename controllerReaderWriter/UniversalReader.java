package controllerReaderWriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public abstract class UniversalReader {
	private FileReader file;
	String filename;
	
	public void openFile () {
		try {
			file = new FileReader(new File(filename));
		} catch (IOException e) {
			System.out.println("Datei "+filename+" konnte nicht gefunden werden.");
			//System.exit(1);
		}
	}

	public void read() {
		String buffer = null;
		BufferedReader buf = new BufferedReader(file);
		
		try {
 
			do  {
				buffer = buf.readLine();
				if (buffer != null) {
					addToListe (buffer);
				}
			} while (buffer !=null);
			end();
		} catch (IOException e) {
			System.out.println("Fehler beim Lesen der Datei "+filename);
			System.exit(1);
		}
	}
	public void end() {};
	abstract void addToListe(String zeile);
	abstract void kompletteListeLaden();

	public void closeFile () {
		if (file != null) {
			try {
				file.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
