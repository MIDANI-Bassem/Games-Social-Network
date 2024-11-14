package org.gameloom.connect.game.game.bll;

import org.gameloom.connect.game.game.bo.Game;
import org.springframework.data.jpa.domain.Specification;

public class GameSpecification {
    public static Specification<Game> withOwnerId(Integer ownerId) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("owner").get("id"), ownerId));
    }
}
