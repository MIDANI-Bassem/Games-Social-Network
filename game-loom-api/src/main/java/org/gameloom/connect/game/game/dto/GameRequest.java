package org.gameloom.connect.game.game.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.gameloom.connect.game.game.bo.GameCategories;

import java.util.Set;

public record GameRequest(
        Integer id,
        @NotNull(message="100")
        @NotEmpty(message="100")
        String name,
        @NotNull(message="101")
        @NotEmpty(message="101")
        String description,
        String yearPublished,
        @NotNull(message="102")
        @NotEmpty(message="102")
        int age,
        int minPlayers,
        int maxPlayers,
        int playingTime,
        String gameDesigner,
        String gameVersion,
        boolean shareable,
        Set<GameCategories> categories

) {
}
