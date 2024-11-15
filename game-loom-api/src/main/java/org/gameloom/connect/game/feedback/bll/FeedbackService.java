package org.gameloom.connect.game.feedback.bll;

import org.gameloom.connect.game.common.PageResponse;
import org.gameloom.connect.game.feedback.dto.FeedbackRequest;
import org.gameloom.connect.game.feedback.dto.FeedbackResponse;
import org.springframework.security.core.Authentication;

public interface FeedbackService {
    Integer save(FeedbackRequest request, Authentication connectedUser);

    PageResponse<FeedbackResponse> findAllFeedbacksByGame(Integer gameId, int page, int size, Authentication connectedUser);
}
