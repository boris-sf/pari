package pari.business.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Guess extends BaseEntity {

	@ManyToOne
	@JsonIgnore
	private User user;
	@ManyToOne
	private Game game;
	@Embedded
	private Score score;

	Guess() {
	}

	public Guess(User user, Game game) {
		this.user = user;
		this.game = game;
	}

	public User getUser() {
		return user;
	}

	public Game getGame() {
		return game;
	}

	public Score getScore() {
		return score;
	}

	public void setScore(Score score) {
		this.score = score;
	}
}