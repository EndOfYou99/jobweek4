package hangman.business.repo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class User {

	@NotEmpty(message = "You must fill this field")
	private String username;

	@NotBlank(message = "You must fill this field")
	private String password;

	public User() {

	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + "]";
	}

}
