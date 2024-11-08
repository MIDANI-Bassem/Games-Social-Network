package org.gameloom.connect.game.user.dal;

import org.gameloom.connect.game.user.bo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
