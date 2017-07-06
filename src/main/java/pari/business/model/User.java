package pari.business.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class User {

	public static final String ROLE_ADMIN = "admin";

	@Id
	private long id;
	private String name;

	User() {
	}

	public User(long id) {
		this.id = id;
	}

	@JsonProperty
	public long id() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}