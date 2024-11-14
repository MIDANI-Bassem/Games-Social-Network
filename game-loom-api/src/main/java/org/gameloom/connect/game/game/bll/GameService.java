package org.gameloom.connect.game.game.bll;

import org.gameloom.connect.game.common.PageResponse;
import org.gameloom.connect.game.game.dto.GameRequest;
import org.springframework.security.core.Authentication;

public interface GameService {
    Integer save(GameRequest request, Authentication connectedUser);

    GameResponse findById(Integer gameId);

    PageResponse<GameResponse> findAllGames(int page, int size, Authentication connectedUser);
}
