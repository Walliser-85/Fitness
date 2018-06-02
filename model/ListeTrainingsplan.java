package model;

import java.util.ArrayList;

import controllerReaderWriter.TrainingsplanLesen;
import controllerReaderWriter.TrainingsplanSchreiben;

public class ListeTrainingsplan {
private ArrayList<Trainingsplan> trainingsplaene;
	
	public ListeTrainingsplan() {
		trainingsplaene = new ArrayList<Trainingsplan>();
	}
	
	public int size() {
		return trainingsplaene.size();
	}
	
	public ArrayList<Trainingsplan> getTrainingsplaene(){
		return trainingsplaene;
	}
	
	public Trainingsplan getTrainingsplan(int index){
		return trainingsplaene.get(index);
	}
	
	public Trainingsplan getTrainingsplan(String trainingsplanname){
		for (Trainingsplan trainingsplan : trainingsplaene) {
			if (trainingsplan.getTitel().equals(trainingsplanname)) {
				return trainingsplan;
			}
		}
		return null;
	}
	
	public void addTrainingsplan(Trainingsplan newTrainingsplan) {
		trainingsplaene.add(newTrainingsplan);
	}
	
	public boolean loadFromFile() {
		try
		{
			TrainingsplanLesen tl = new TrainingsplanLesen();
			trainingsplaene = tl.getTrainingsplan();
			tl.closeFile();
			return true;
		}
		catch (Exception e){
			return false;
		}
	}

	public boolean saveList() {
		System.out.println("SAVE LIST");
		TrainingsplanSchreiben ts = new TrainingsplanSchreiben();
		if (ts.writeList(this)) {
			ts.close();
			return true;
		}
		else {
			ts.close();
			return false;
		}
	}
	
	public void delete(int index) {
		trainingsplaene.remove(index);
	}
}
