package pari.auth;

public class Principal {

	private long id;

	public long getId() {
		return id;
	}

	public boolean hasRole(String role) {
		return false;
	}
}