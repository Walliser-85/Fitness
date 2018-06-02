package controllerReaderWriter;

import java.sql.Date;
import java.util.ArrayList;

import model.Kunde;
import model.ListeUebungTrainingsplan;
import model.Trainingsplan;
import model.UebungTrainingsplan;

public class TrainingsplanLesen extends UniversalReader{
	private ArrayList<Trainingsplan> trainingsplaene = new ArrayList<Trainingsplan>();
	private int id = 0;
	private boolean first = true;
	private String [] dataTrainingsplan = new String[5];
	private ListeUebungTrainingsplan utrainingsplan; 
	public TrainingsplanLesen() {
		this.filename = "Trainingsplaene.txt";
		utrainingsplan = new ListeUebungTrainingsplan();
	}
	
	/**
	 * @return the muskelgruppen
	 */
	public ArrayList<Trainingsplan> getTrainingsplan() {
		kompletteListeLaden();
		return trainingsplaene;
	}
	
	public void end() {		
		Trainingsplan trainingsplan = new Trainingsplan(Integer.parseInt(dataTrainingsplan[0]), dataTrainingsplan[1], 
				dataTrainingsplan[2], dataTrainingsplan[3], Kunde.parseKunde(dataTrainingsplan[4]), utrainingsplan);
		trainingsplaene.add(trainingsplan);
	}
	
	@Override
	void addToListe(String zeile) {
		String [] data = zeile.split("#");
		try {
			if(id != Integer.parseInt(data[0]) && first) {
				first = false;
				id = Integer.parseInt(data[0]);
				
				dataTrainingsplan[0] = data[0];
				dataTrainingsplan[1] = data[1];
				dataTrainingsplan[2] = data[2];
				dataTrainingsplan[3] = data[3];
				dataTrainingsplan[4] = data[4];
			}
			else if(id != Integer.parseInt(data[0]) && !first) {
				id = Integer.parseInt(data[0]);
				
				Trainingsplan trainingsplan = new Trainingsplan(Integer.parseInt(dataTrainingsplan[0]), dataTrainingsplan[1], 
						dataTrainingsplan[2], dataTrainingsplan[3], Kunde.parseKunde(dataTrainingsplan[4]), utrainingsplan);
				trainingsplaene.add(trainingsplan);
				
				utrainingsplan = new ListeUebungTrainingsplan();
				
				dataTrainingsplan[0] = data[0];
				dataTrainingsplan[1] = data[1];
				dataTrainingsplan[2] = data[2];
				dataTrainingsplan[3] = data[3];
				dataTrainingsplan[4] = data[4];
			}
			else if(id == Integer.parseInt(data[0])){
				UebungTrainingsplan newUebung = new UebungTrainingsplan(Integer.parseInt(data[1]), data[2], data[3], 
						UebungTrainingsplan.parseMuskelgruppe(data[4]), Integer.parseInt(data[5]), Integer.parseInt(data[6]));
				utrainingsplan.addUebung(newUebung);
			}
		}catch(ArrayIndexOutOfBoundsException e) {
			System.err.println("Fehlerhaftes Datenformat");
		}
	}

	@Override
	void kompletteListeLaden() {
		openFile();
		trainingsplaene = new ArrayList<Trainingsplan>();
		read();
		closeFile();
	}
}
