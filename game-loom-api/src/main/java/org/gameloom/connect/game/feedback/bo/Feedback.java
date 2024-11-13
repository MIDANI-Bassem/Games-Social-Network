package org.gameloom.connect.game.feedback.bo;

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

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class Feedback extends BaseEntity {

    private Double note;
    private String message;
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

}
