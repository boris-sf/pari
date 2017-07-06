package pari.business.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pari.business.model.Guess;

@Repository
public interface GuessDao extends JpaRepository<Guess, Long> {

	@Query("select g from Guess g where g.game.id = :gameId and g.user.id = :userId")
	Guess lookup(@Param("gameId") long gameId, @Param("userId") long userId);
}