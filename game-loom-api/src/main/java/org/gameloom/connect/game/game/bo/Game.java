package org.gameloom.connect.game.game.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.gameloom.connect.game.common.BaseEntity;
import org.gameloom.connect.game.feedback.bo.Feedback;
import org.gameloom.connect.game.history.bo.GameTrack;
import org.gameloom.connect.game.user.bo.User;

import java.security.PublicKey;
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
    private double rate;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    @JsonIgnore
    private User owner;

    @OneToMany(mappedBy = "game")
    private List<Feedback> feedbacks = new ArrayList<>();

    @OneToMany(mappedBy = "game")
    private List<GameTrack> transactions = new ArrayList<>();

    @Transient
    public double getRate(){
        if(feedbacks == null || feedbacks.isEmpty()){
            return 0.0;
        }
        var rate = feedbacks.stream()
                .mapToDouble(Feedback::getNote)
                .average()
                .orElse(0.0);
        return Math.round(rate*10.0)/10.0;
    }

}
