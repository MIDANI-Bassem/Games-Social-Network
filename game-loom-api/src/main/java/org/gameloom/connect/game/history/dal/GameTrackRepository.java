package org.gameloom.connect.game.history.dal;

import org.gameloom.connect.game.history.bo.GameTrack;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameTrackRepository extends JpaRepository<GameTrack, Integer> {
    @Query(
            """
            SELECT transactions 
            FROM GameTrack transactions
            WHERE transactions.user.id = :userId
            """
    )
    Page<GameTrack> findAllBorrowedGames(Pageable pageable, Integer userId);


    @Query(
            """
            SELECT transactions 
            FROM GameTrack transactions
            WHERE transactions.game.owner.id = :userId
            """
    )
    Page<GameTrack> findAllReturnedGames(Pageable pageable, Integer userId);



    @Query(
            """
            SELECT 
            ( COUNT(*)>0) AS isBorrowed
            FROM GameTrack transactions
            WHERE transactions.user.id = :userId
            AND transactions.game.id = :gameId
            AND transactions.returnApproved = false
            """
    )
    boolean isAlreadyBorrowed(Integer gameId, Integer userId);


    @Query(
            """
            SELECT transactions 
            FROM GameTrack transactions
            WHERE transactions.game.id = :gameId
            AND transactions.user.id = :userId
            AND transactions.returned = false
            AND transactions.returnApproved = false
            """
    )
    Optional<GameTrack> findByGameIdAndUserId(Integer gameId, Integer userId);



    @Query(
            """
            SELECT transactions 
            FROM GameTrack transactions
            WHERE transactions.game.id = :gameId
            AND transactions.game.owner.id = :userId
            AND transactions.returned = true
            AND transactions.returnApproved = false
            """
    )
    Optional<GameTrack> findByGameIdAndOwnerId(Integer gameId, Integer userId);
}
