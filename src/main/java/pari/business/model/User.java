package pari.business.model;

import static java.lang.String.format;

import javax.persistence.Entity;

@Entity
public class User extends BaseEntity {

	private String name;

	public User() {
	}

	public User(String name) {
		setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return format("%s, name=%s", super.toString(), name);
	}
}