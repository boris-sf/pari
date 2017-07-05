package pari.business.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pari.business.model.Game;

@Repository
public interface GameDao extends JpaRepository<Game, Long> {

}