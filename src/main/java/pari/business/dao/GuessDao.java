package pari.business.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pari.business.model.Guess;

@Repository
public interface GuessDao extends JpaRepository<Guess, Long> {

	@Query("select g from Guess g where g.user.id = :userId and g.game.startDate > :startDate")
	List<Guess> lookup(@Param("userId") long userId, @Param("startDate") Date startDate);

	@Query("select g from Guess g where g.user.id = :userId and g.game.id = :gameId")
	Guess lookup(@Param("userId") long userId, @Param("gameId") long gameId);
}