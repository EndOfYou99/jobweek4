package hangman.business.repo.achievements;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import hangman.business.repo.user.User;

@Entity
public class Achievements {
	@Id
	int id;

	String achievement;

	@ManyToMany(cascade = CascadeType.ALL)
	List<User> users;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAchievement() {
		return achievement;
	}

	public void setAchievement(String achievement) {
		this.achievement = achievement;
	}

	@Override
	public String toString() {
		return "Achievements [id=" + id + ", achievement=" + achievement + ", users=" + users + "]";
	}

}
