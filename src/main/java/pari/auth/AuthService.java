package pari.auth;

import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;
import static org.springframework.web.context.WebApplicationContext.SCOPE_REQUEST;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import pari.business.model.User;

@Component
@Scope(scopeName = SCOPE_REQUEST, proxyMode = TARGET_CLASS)
public class AuthService {

	private User currentUser = new User() {
		public boolean hasRole(String role) {
			return true;
		};
	};

	public void init(User currentUser) {
		this.currentUser = currentUser;
	}

	public User currentUser() {
		return currentUser;
	}
}