package pari.auth;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import pari.business.model.User;

@Component
public class AuthServletFilter extends OncePerRequestFilter {

	@Autowired
	private AuthService auth;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		final String header = request.getHeader("Pari-Auth");
		if (header != null) {
			auth.init(new ObjectMapper().readerFor(User.class).readValue(header));
		}
		chain.doFilter(request, response);
	}
}