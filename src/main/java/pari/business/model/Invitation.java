package pari.business.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Invitation extends BaseEntity {

	public enum Status {
		pending, accepted, declined;
	}

	private Status status = Status.pending;
	@ManyToOne
	@JsonIgnore
	private User from;
	@ManyToOne
	private User user;
	@ManyToOne
	private Game game;

	Invitation() {
	}

	public Invitation(User from, User user, Game game) {
		this.from = from;
		this.user = user;
		this.game = game;
	}

	public Status getStatus() {
		return status;
	}

	public User getFrom() {
		return from;
	}

	public User getUser() {
		return user;
	}

	public Game getGame() {
		return game;
	}
}