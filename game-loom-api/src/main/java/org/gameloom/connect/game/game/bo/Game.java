package org.gameloom.connect.game.game.bo;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.gameloom.connect.game.common.BaseEntity;
import org.gameloom.connect.game.feedback.bo.Feedback;
import org.gameloom.connect.game.history.GameTrack;
import org.gameloom.connect.game.user.bo.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class Game extends BaseEntity {

    private String name;
    private String description;


    @ManyToMany
    @JoinTable(
            name = "game_category",
            joinColumns = @JoinColumn(name = "game_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id")
    )
    @JsonManagedReference
    private Set<GameCategories> gameCategories = new HashSet<>();



    private String yearPublished;
    private int age;
    private int minPlayers;
    private int maxPlayers;
    private int playingTime;
    private String image;
    private String gameDesigner;
    private String gameVersion;
    private boolean shareable;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToMany(mappedBy = "game")
    private List<Feedback> feedbacks = new ArrayList<>();

    @OneToMany(mappedBy = "game")
    private List<GameTrack> transactions = new ArrayList<>();



}
