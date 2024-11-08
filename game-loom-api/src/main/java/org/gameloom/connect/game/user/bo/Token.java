package org.gameloom.connect.game.user.bo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer id;
    private String token;
    private LocalDateTime created;
    private LocalDateTime expires;
    private LocalDateTime issued;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
