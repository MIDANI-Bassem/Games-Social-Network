package org.gameloom.connect.game.game.bll;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.gameloom.connect.game.common.PageResponse;
import org.gameloom.connect.game.game.bo.Game;
import org.gameloom.connect.game.game.dal.GameRepository;
import org.gameloom.connect.game.game.dto.GameRequest;
import org.gameloom.connect.game.user.bo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final GameMapper gameMapper;


    /**
     * @param request
     * @param connectedUser
     * @return
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
     * @return
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
     * @return
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
}
