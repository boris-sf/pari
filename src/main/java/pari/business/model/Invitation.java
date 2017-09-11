package pari.business.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
public class Invitation extends BaseEntity {

	public enum Status {
		pending, accepted, declined;
	}

	private Status status = Status.pending;

	@ManyToOne
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
	private User from;

	@ManyToOne
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
	private User user;

	@ManyToOne
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
	private Game game;

	Invitation() {
	}

	public Invitation(User from, User user, Game game) {
		this.from = from;
		this.user = user;
		this.game = game;
	}

	public Invitation accept() {
		this.status = Status.accepted;
		return this;
	}

	public Invitation decline() {
		this.status = Status.declined;
		return this;
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