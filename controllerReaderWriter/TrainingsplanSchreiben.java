package controllerReaderWriter;

import java.io.File;
import java.io.IOException;

import model.ListeTrainingsplan;
import model.Trainingsplan;

public class TrainingsplanSchreiben extends UniversalWriter{
	public TrainingsplanSchreiben() {
		super(new File("Trainingsplaene.txt"));
	}
	
	public void write(Trainingsplan trainingsplan) {
		try {
			String trainingsplanTXT = trainingsplan.toString();
			out.write(trainingsplanTXT);
		} catch (IOException e) {
			System.out.println("Fehler beim Schreiben.");
		}
	}
	
	public boolean writeList(ListeTrainingsplan trainingsplaene) {
		
		try {
			for(int i=0; i<trainingsplaene.size(); i++) {
				write(trainingsplaene.getTrainingsplan(i));
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
