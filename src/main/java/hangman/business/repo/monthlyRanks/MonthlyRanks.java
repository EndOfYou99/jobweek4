package hangman.business.repo.monthlyRanks;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import hangman.business.repo.statistics.Statistics;

@Entity
public class MonthlyRanks {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int mid;

	@Column(name = "user")
	private String muser;

	@Column(name = "playedGames")
	private int mplayedGames;

	@Column(name = "wins")
	private int mwins;

	@Column(name = "loses")
	private int mloses;

	@Column(name = "easyGames")
	private int measyGames;

	@Column(name = "mediumGames")
	private int mmediumGames;

	@Column(name = "hardGames")
	private int mhardGames;

	@OneToMany(mappedBy = "monthlyRanks")
	List<Statistics> mstatistics = new ArrayList();

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public String getMuser() {
		return muser;
	}

	public void setMuser(String muser) {
		this.muser = muser;
	}

	public int getMplayedGames() {
		return mplayedGames;
	}

	public void setMplayedGames(int mplayedGames) {
		this.mplayedGames = mplayedGames;
	}

	public int getMwins() {
		return mwins;
	}

	public void setMwins(int mwins) {
		this.mwins = mwins;
	}

	public int getMloses() {
		return mloses;
	}

	public void setMloses(int mloses) {
		this.mloses = mloses;
	}

	public int getMeasyGames() {
		return measyGames;
	}

	public void setMeasyGames(int measyGames) {
		this.measyGames = measyGames;
	}

	public int getMmediumGames() {
		return mmediumGames;
	}

	public void setMmediumGames(int mmediumGames) {
		this.mmediumGames = mmediumGames;
	}

	public int getMhardGames() {
		return mhardGames;
	}

	public void setMhardGames(int mhardGames) {
		this.mhardGames = mhardGames;
	}

	public List<Statistics> getMstatistics() {
		return mstatistics;
	}

	public void setMstatistics(List<Statistics> mstatistics) {
		this.mstatistics = mstatistics;
	}

	@Override
	public String toString() {
		return "[" + muser + " - wins: " + mwins + "]";
	}

}
