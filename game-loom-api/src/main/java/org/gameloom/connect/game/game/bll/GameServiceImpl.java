package org.gameloom.connect.game.game.bll;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.gameloom.connect.game.common.PageResponse;
import org.gameloom.connect.game.game.bo.Game;
import org.gameloom.connect.game.game.dal.GameRepository;
import org.gameloom.connect.game.game.dto.GameRequest;
import org.gameloom.connect.game.history.bo.GameTrack;
import org.gameloom.connect.game.history.dal.GameTrackRepository;
import org.gameloom.connect.game.user.bo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final GameMapper gameMapper;
    private final GameTrackRepository gameTrackRepository;


    /**
     * @param request
     * @param connectedUser
     * @return saved gameId
     */
    @Override
    public Integer save(GameRequest request, Authentication connectedUser) {
        User user = (User) connectedUser.getPrincipal();
        Game game = gameMapper.togame(request);
        game.setOwner(user);
        return gameRepository.save(game).getId();
    }

    /**
     * @param gameId
     * @return game
     */
    @Override
    public GameResponse findById(Integer gameId) {
        return gameRepository.findById(gameId)
                .map(gameMapper::toGameResponse)
                .orElseThrow(()-> new EntityNotFoundException("No game found with this Id = " + gameId))
                ;
    }

    /**
     * @param page
     * @param size
     * @param connectedUser
     * @return AllGames
     */
    @Override
    public PageResponse<GameResponse> findAllGames(int page, int size, Authentication connectedUser) {
        User user = (User) connectedUser.getPrincipal();
        Pageable pageable = PageRequest.of(page, size, Sort.by("creationDate").descending());
        Page<Game> games = gameRepository.findAllDisplayableBooks(pageable, user.getId());
        List<GameResponse> gameResponse = games.stream().map(gameMapper::toGameResponse).toList();

        return new PageResponse<>(
                gameResponse,
                games.getNumber(),
                games.getSize(),
                games.getTotalElements(),
                games.getTotalPages(),
                games.isFirst(),
                games.isLast()
        );
    }

    /**
     * @param page
     * @param size
     * @param connectedUser
     * @return AllGamesByOwner
     */
    @Override
    public PageResponse<GameResponse> findAllGamesByOwner(int page, int size, Authentication connectedUser) {
        User user = (User) connectedUser.getPrincipal();
        Pageable pageable = PageRequest.of(page, size, Sort.by("creationDate").descending());
        Page<Game> games = gameRepository.findAll(GameSpecification.withOwnerId(user.getId()), pageable);
        List<GameResponse> gameResponse = games.stream().map(gameMapper::toGameResponse).toList();

        return new PageResponse<>(
                gameResponse,
                games.getNumber(),
                games.getSize(),
                games.getTotalElements(),
                games.getTotalPages(),
                games.isFirst(),
                games.isLast()
        );
    }

    /**
     * @param page
     * @param size
     * @param connectedUser
     * @return
     */
    @Override
    public PageResponse<BorrowedGameResponse> findAllBorrowedGames(int page, int size, Authentication connectedUser) {
        User user = (User) connectedUser.getPrincipal();
        Pageable pageable = PageRequest.of(page, size, Sort.by("creationDate").descending());
        Page<GameTrack> allBorrowedGames = gameTrackRepository.findAllBorrowedGames(pageable, user.getId());
        List<BorrowedGameResponse> gameResponse = allBorrowedGames.stream().map(gameMapper::toBorrowedGameResponse).toList();
        return new PageResponse<>(
                gameResponse,
                allBorrowedGames.getNumber(),
                allBorrowedGames.getSize(),
                allBorrowedGames.getTotalElements(),
                allBorrowedGames.getTotalPages(),
                allBorrowedGames.isFirst(),
                allBorrowedGames.isLast()
        );
    }

    /**
     * @param page
     * @param size
     * @param connectedUser
     * @return
     */
    @Override
    public PageResponse<BorrowedGameResponse> findAllReturnedGames(int page, int size, Authentication connectedUser) {
        User user = (User) connectedUser.getPrincipal();
        Pageable pageable = PageRequest.of(page, size, Sort.by("creationDate").descending());
        Page<GameTrack> allBorrowedGames = gameTrackRepository.findAllReturnedGames(pageable, user.getId());
        List<BorrowedGameResponse> gameResponse = allBorrowedGames.stream().map(gameMapper::toBorrowedGameResponse).toList();
        return new PageResponse<>(
                gameResponse,
                allBorrowedGames.getNumber(),
                allBorrowedGames.getSize(),
                allBorrowedGames.getTotalElements(),
                allBorrowedGames.getTotalPages(),
                allBorrowedGames.isFirst(),
                allBorrowedGames.isLast()
        );
    }
}
