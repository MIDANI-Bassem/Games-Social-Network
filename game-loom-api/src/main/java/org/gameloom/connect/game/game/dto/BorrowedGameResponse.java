package org.gameloom.connect.game.game.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BorrowedGameResponse {
    private Integer id;
    private String name;
    private String description;
    private double rate;
    private boolean returned;
    private boolean returnApproved;

}
