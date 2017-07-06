package pari.business.model;

import static java.lang.String.format;

import javax.persistence.Entity;

@Entity
public class User extends BaseEntity {

	public static final String ROLE_ADMIN = "admin";

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean hasRole(String role) {
		return false;
	}

	@Override
	public String toString() {
		return format("%s, name=%s", super.toString(), name);
	}
}