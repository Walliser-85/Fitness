package controllerGUI;

import model.Benutzer;
import model.ListeBenutzer;
import viewGUI.FitnessManager;
import viewGUI.Login;

public class LoginController {
private ListeBenutzer lb;
	
	public LoginController() {
		lb = new ListeBenutzer();
		lb.loadFromFile();
	}
	
	public String checkLogin(String benutzername, String passwort, Login frameLogin) {
		Benutzer user = lb.getBenutzer(benutzername);
		if(user == null) {
			return "Falscher Benutzername";
		}
		else {
			if(user.getPasswort().equals(passwort)) {
				FitnessManager frame = new FitnessManager(user);
				frame.setVisible(true);
				frameLogin.dispose();
				return "Login korrekt";
			}
			else {
				return "Falsches Passwort";
			}
		}
	}
}
