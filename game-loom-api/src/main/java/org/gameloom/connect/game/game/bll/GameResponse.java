package org.gameloom.connect.game.game.bll;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gameloom.connect.game.game.bo.GameCategories;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GameResponse {
  private Integer id;
  private String name;
  private String owner;
  private String description;
  private int age;
  private int minPlayers;
  private int maxPlayers;
  private int playingTime;
  private String gameDesigner;
  private String gameVersion;
  private String yearPublished;
  private boolean shareable;
  private double rate;
  private Set<GameCategories> categories;
  private byte[] image;


}
