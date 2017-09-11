package pari.web.controller;

import static java.lang.String.valueOf;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pari.business.model.Invitation;
import pari.business.model.Invitation.Status;
import pari.business.service.InvitationService;
import pari.web.dto.NewInvitationDto;

@CrossOrigin
@RestController
@RequestMapping("/invitation")
public class InvitationController {

	@Autowired
	private InvitationService invitations;

	@PostMapping
	public Invitation create(@RequestBody NewInvitationDto invitation) {
		return invitations.create(invitation.getUserId(), invitation.getGameId());
	}

	@GetMapping
	public List<Invitation> activeInvitations() {
		return invitations.active();
	}

	@PutMapping("/{id}")
	public Invitation update(@PathVariable("id") long id, @RequestBody Status status) {
		switch (status) {
		case accepted:
			return invitations.accept(id);
		case declined:
			return invitations.decline(id);
		default:
			throw new UnsupportedOperationException(valueOf(status));
		}
	}
}