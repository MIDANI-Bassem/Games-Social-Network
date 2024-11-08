package org.gameloom.connect.game.user.dal;

import org.gameloom.connect.game.user.bo.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {
    Optional<Token> findByToken(String token);
}
