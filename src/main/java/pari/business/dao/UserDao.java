package pari.business.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pari.business.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
}