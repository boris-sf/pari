package pari.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pari.business.model.Invitation;
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
}