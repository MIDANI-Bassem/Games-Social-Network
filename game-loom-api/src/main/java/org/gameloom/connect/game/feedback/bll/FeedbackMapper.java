package org.gameloom.connect.game.feedback.bll;

import org.gameloom.connect.game.feedback.bo.Feedback;
import org.gameloom.connect.game.feedback.dto.FeedbackRequest;
import org.gameloom.connect.game.feedback.dto.FeedbackResponse;
import org.gameloom.connect.game.game.bo.Game;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class FeedbackMapper {
    public Feedback toFeedback(FeedbackRequest request) {
        return Feedback.builder()
                .note(request.note())
                .message(request.comment())
                .game(Game.builder()
                        .id(request.gameId())
                        .build()
                )
                .build();
    }



    public FeedbackResponse toFeedbackResponse(Feedback feedback, Integer userId) {
        return FeedbackResponse.builder()
                .note(feedback.getNote())
                .message(feedback.getMessage())
                .ownFeedback(Objects.equals(feedback.getCreatedBy(), userId))
                .build();
    }

}
