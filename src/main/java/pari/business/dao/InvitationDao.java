package pari.business.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pari.business.model.Invitation;
import pari.business.model.Invitation.Status;

@Repository
public interface InvitationDao extends JpaRepository<Invitation, Long> {

	@Query("select iv from Invitation iv where iv.from.id = :fromId and iv.user.id = :userId and iv.game.id = :gameId")
	Invitation lookup(@Param("fromId") long fromId, @Param("userId") long userId, @Param("gameId") long gameId);

	@Query("select iv from Invitation iv where iv.user.id = :userId and iv.game.startDate >= :startDate and iv.status in :status")
	List<Invitation> lookup(@Param("userId") long userId, @Param("startDate") Date startDate,
			@Param("status") List<Status> status);
}