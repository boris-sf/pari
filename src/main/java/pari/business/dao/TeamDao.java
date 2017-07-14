package pari.business.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pari.business.model.Team;

@Repository
public interface TeamDao extends JpaRepository<Team, Long> {

	@Query("select t from Team t where lower(t.name) like lower(:name) order by t.id asc")
	List<Team> lookup(@Param("name") String name, Pageable page);
}