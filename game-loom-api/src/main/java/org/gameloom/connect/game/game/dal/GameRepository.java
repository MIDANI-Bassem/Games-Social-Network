package org.gameloom.connect.game.game.dal;

import org.gameloom.connect.game.game.bo.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer>, JpaSpecificationExecutor<Game> {
    @Query(
    """
    SELECT game FROM Game game
    WHERE game.shareable = true
    AND game.owner.id != :userId 
    """
    )
    Page<Game> findAllDisplayableBooks(Pageable pageable, Integer userId);
}
