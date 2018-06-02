package controllerReaderWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class UniversalWriter {
	FileWriter out;
	File file;

	public UniversalWriter (File file) {
		try {
			out= new FileWriter (file,false); 			
		}
		catch (IOException e) {
			System.out.println("Datei "+file.getName()+" konnte nicht erstellt werden");
			System.exit(1);
		}
	}

	public void close () {
		if (out != null)
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
}
