package hangman.business.repo.ranks;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import hangman.business.repo.statistics.Statistics;

@Entity
public class Ranks {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String user;
	private int playedGames;
	private int wins;
	private int loses;
	private int easyGames;
	private int mediumGames;
	private int hardGames;

	@OneToMany(mappedBy = "ranks")
	List<Statistics> statistics = new ArrayList();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public int getPlayedGames() {
		return playedGames;
	}

	public void setPlayedGames(int playedGames) {
		this.playedGames = playedGames;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getLoses() {
		return loses;
	}

	public void setLoses(int loses) {
		this.loses = loses;
	}

	public int getEasyGames() {
		return easyGames;
	}

	public void setEasyGames(int easyGames) {
		this.easyGames = easyGames;
	}

	public int getMediumGames() {
		return mediumGames;
	}

	public void setMediumGames(int mediumGames) {
		this.mediumGames = mediumGames;
	}

	public int getHardGames() {
		return hardGames;
	}

	public void setHardGames(int hardGames) {
		this.hardGames = hardGames;
	}

	@Override
	public String toString() {
		return "[" + user + " -  wins: " + wins + "]";
	}

}
