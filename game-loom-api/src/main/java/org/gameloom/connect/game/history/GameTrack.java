package org.gameloom.connect.game.history;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.gameloom.connect.game.common.BaseEntity;
import org.gameloom.connect.game.game.bo.Game;
import org.gameloom.connect.game.user.bo.User;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class GameTrack extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;
    private boolean returned;
    private boolean returnApproved;

}
