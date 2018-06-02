package controllerGUI;

public class ControllerVorlage {
	public static String user;
	
	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}
	public boolean checkStandartName(String eingabe) {
		return eingabe.matches("[A-ZÄÖÜ][A-ZÄÖÜa-zöäü\\s]*");
	}
	public boolean checkEmail(String email) {
		return email.matches("[\\w|-]+@\\w[\\w|-]*\\.[a-z]{2,3}");
	}
}
