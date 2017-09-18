package pari.web.dto;

import pari.business.model.Invitation.Status;

public class UpdateInvitationStatusDto {

	private Status status;

	public Status getStatus() {
		return status;
	}
}