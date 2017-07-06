package pari.auth;

import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;
import static org.springframework.web.context.WebApplicationContext.SCOPE_REQUEST;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = SCOPE_REQUEST, proxyMode = TARGET_CLASS)
public class AuthService {

	private Principal principal = new Principal() {
		public boolean hasRole(String role) {
			return true;
		};
	};

	public void init(Principal principal) {
		this.principal = principal;
	}

	public Principal principal() {
		return principal;
	}
}