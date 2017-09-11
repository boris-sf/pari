package pari.business.service;

import static java.lang.String.format;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pari.business.dao.GameDao;
import pari.business.dao.InvitationDao;
import pari.business.model.Game;
import pari.business.model.Invitation;
import pari.business.model.User;

@Service
public class InvitationService {

	@Autowired
	private InvitationDao invitations;
	@Autowired
	private UserService users;
	@Autowired
	private GameDao games;

	public Invitation create(long userId, long gameId) {
		final Game game = games.findOne(gameId);
		if (game == null) {
			throw new IllegalArgumentException(format("Game with id=%s not found", gameId));
		}
		if (game.overdue()) {
			throw new IllegalStateException("Game is already over");
		}
		final User user = users.currentUser();
		final Invitation invitation = invitations.lookup(user.id(), userId, gameId);
		return invitation == null ? invitations.save(new Invitation(user, users.find(userId), game)) : invitation;
	}

	public List<Invitation> active() {
		final User user = users.currentUser();
		return invitations.lookup(user.id(), new Date());
	}
}