package pari.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pari.auth.AuthService;
import pari.business.dao.UserDao;
import pari.business.model.User;

@Service
public class UserService {

	@Autowired
	private AuthService auth;
	@Autowired
	private UserDao users;

	public User currentUser() {
		final long id = auth.principal().getId();
		final User user = users.findOne(id);
		return user == null ? users.save(new User(id)) : user;
	}
}