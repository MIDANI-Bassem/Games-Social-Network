package org.gameloom.connect.game.game.bll;

import org.gameloom.connect.game.common.PageResponse;
import org.gameloom.connect.game.game.dto.GameRequest;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

public interface GameService {
    Integer save(GameRequest request, Authentication connectedUser);

    GameResponse findById(Integer gameId);

    PageResponse<GameResponse> findAllGames(int page, int size, Authentication connectedUser);

    PageResponse<GameResponse> findAllGamesByOwner(int page, int size, Authentication connectedUser);

    PageResponse<BorrowedGameResponse> findAllBorrowedGames(int page, int size, Authentication connectedUser);

    PageResponse<BorrowedGameResponse> findAllReturnedGames(int page, int size, Authentication connectedUser);

    Integer updateShareableStatus(Integer gameId, Authentication connectedUser);

    Integer borrowGame(Integer gameId, Authentication connectedUser);

    Integer returnGame(Integer gameId, Authentication connectedUser);

    Integer approveReturnGame(Integer gameId, Authentication connectedUser);

    void uploadGameImage(MultipartFile file, Authentication connectedUser, Integer gameId);
}
