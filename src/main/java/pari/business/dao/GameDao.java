package pari.business.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pari.business.model.Game;

@Repository
public interface GameDao extends JpaRepository<Game, Long> {

	@Query("select g from Game g where g.startDate >= :startDate order by g.startDate")
	List<Game> upcoming(@Param("startDate") Date startDate);

	@Query("select g from Game g order by g.startDate")
	List<Game> all();

}