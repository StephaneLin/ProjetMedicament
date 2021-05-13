package modele;

public class LoginButtonEvent {
	
	private String login, pass;
	
		public LoginButtonEvent(String login, String pass) {
			this.login = login;
			this.pass = pass;
		}

		public String getLogin() {
			return login;
		}

		public String getPass() {
			return pass;
		}
}
