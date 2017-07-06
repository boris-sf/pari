package pari.business.service;

import static java.lang.String.format;
import static pari.business.model.User.ROLE_ADMIN;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import pari.business.dao.TeamDao;
import pari.business.model.Team;

@Service
public class TeamService {

	@Autowired
	private TeamDao teams;

	public List<Team> lookup(String name) {
		return teams.lookup(format("%%%s%%", name), new PageRequest(0, 10));
	}

	@RolesAllowed(ROLE_ADMIN)
	public Team create(String name, String logo) {
		final Team team = new Team();
		team.setName(name);
		team.setLogo(logo);
		return teams.save(team);
	}

	@RolesAllowed(ROLE_ADMIN)
	public void delete(long id) {
		final Team team = teams.findOne(id);
		if (team != null) {
			teams.delete(team);
		}
	}
}