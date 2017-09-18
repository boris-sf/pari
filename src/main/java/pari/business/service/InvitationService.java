package pari.business.service;

import static java.lang.String.format;
import static pari.business.model.Invitation.Status.accepted;
import static pari.business.model.Invitation.Status.pending;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pari.business.dao.GameDao;
import pari.business.dao.InvitationDao;
import pari.business.model.Game;
import pari.business.model.Invitation;
import pari.business.model.Invitation.Status;
import pari.business.model.User;

@Service
public class InvitationService {

	private static final List<Status> activeStatus = Arrays.asList(pending, accepted);

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
		if (user.id() == userId) {
			throw new IllegalArgumentException("Self invites are not supported");
		}
		final Invitation invitation = invitations.lookup(user.id(), userId, gameId);
		return invitation == null ? invitations.save(new Invitation(user, users.find(userId), game)) : invitation;
	}

	public List<Invitation> active() {
		return invitations.lookup(users.currentUser().id(), new Date(), activeStatus);
	}

	public Invitation accept(long id) {
		return invitations.save(findForUpdate(id).accept());
	}

	public Invitation decline(long id) {
		return invitations.save(findForUpdate(id).decline());
	}

	private Invitation findForUpdate(long id) {
		final Invitation invitation = invitations.findOne(id);
		if (invitation == null) {
			throw new IllegalArgumentException(format("Invitation with id=%s not found", id));
		}
		if (invitation.getUser().id() != users.currentUser().id()) {
			throw new IllegalArgumentException("Cannot update other user's invitation");
		}
		return invitation;
	}
}