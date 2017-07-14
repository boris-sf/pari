package pari.business.service;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import pari.business.dao.TeamDao;
import pari.business.model.Team;

import javax.annotation.security.RolesAllowed;
import java.util.List;

import static java.lang.String.format;
import static pari.business.model.User.ROLE_ADMIN;

@Service
@Transactional
public class TeamService {

	@Autowired
	private TeamDao teams;

	@Transactional(readOnly = true)
	public List<Team> lookup(String name) {
		return teams.lookup(format("%%%s%%", name), new PageRequest(0, Integer.MAX_VALUE));
	}

	@RolesAllowed(ROLE_ADMIN)
	@Transactional(readOnly = true)
	public List<Team> findAll() {
		return teams.findAll(new Sort(Sort.Direction.DESC, "id"));
	}

	@Transactional
	public Team create(String name, String logo) {
		final Team team = new Team();
		team.setName(name);
		team.setLogo(logo);
		return teams.save(team);
	}

	@RolesAllowed(ROLE_ADMIN)
	@Transactional
	public Team update(Team team) {
		return teams.saveAndFlush(team);
	}

	@RolesAllowed(ROLE_ADMIN)
	@Transactional
	public void delete(long id) {
		final Team team = teams.findOne(id);
		if (team != null) {
			teams.delete(team);
		}
	}

}