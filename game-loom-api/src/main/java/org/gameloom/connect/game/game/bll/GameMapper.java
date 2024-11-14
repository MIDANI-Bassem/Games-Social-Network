package org.gameloom.connect.game.game.bll;

import org.gameloom.connect.game.game.bo.Game;
import org.gameloom.connect.game.game.bo.GameCategories;
import org.gameloom.connect.game.game.dto.GameRequest;
import org.gameloom.connect.game.history.bo.GameTrack;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class GameMapper {
    public Game togame(GameRequest request) {
        return Game.builder()
                .id(request.id())
                .name(request.name())
                .description(request.description())
                .maxPlayers(request.maxPlayers())
                .gameVersion(request.gameVersion())
                .age(request.age())
                .minPlayers(request.minPlayers())
                .playingTime(request.playingTime())
                .gameDesigner(request.gameDesigner())
                .yearPublished(request.yearPublished())
                .shareable(request.shareable())
                .gameCategories(request.categories() != null ? request.categories() : null)
                .build();
    }

    public GameResponse toGameResponse(Game game) {
        return GameResponse.builder()
                .id(game.getId())
                .name(game.getName())
                .description(game.getDescription())
                .maxPlayers(game.getMaxPlayers())
                .gameVersion(game.getGameVersion())
                .age(game.getAge())
                .minPlayers(game.getMinPlayers())
                .playingTime(game.getPlayingTime())
                .gameDesigner(game.getGameDesigner())
                .yearPublished(game.getYearPublished())
                .shareable(game.isShareable())
                .owner(game.getOwner().toString())
                .rate(game.getRate())
                .categories(game.getGameCategories() != null ? game.getGameCategories() : null)
                //.image(game.getImage())

                .build();
    }

    public BorrowedGameResponse toBorrowedGameResponse(GameTrack gameTrack) {
        return BorrowedGameResponse.builder()
                .id(gameTrack.getGame().getId())
                .name(gameTrack.getGame().getName())
                .description(gameTrack.getGame().getDescription())
                .rate(gameTrack.getGame().getRate())
                .returned(gameTrack.isReturned())
                .returnApproved(gameTrack.isReturnApproved())
                .build();

    }
}
