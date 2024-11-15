package org.gameloom.connect.game.feedback.bll;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.gameloom.connect.game.common.PageResponse;
import org.gameloom.connect.game.exception.OperationNotPermittedException;
import org.gameloom.connect.game.feedback.bo.Feedback;
import org.gameloom.connect.game.feedback.dal.FeedbackRepository;
import org.gameloom.connect.game.feedback.dto.FeedbackRequest;
import org.gameloom.connect.game.feedback.dto.FeedbackResponse;
import org.gameloom.connect.game.game.bo.Game;
import org.gameloom.connect.game.game.dal.GameRepository;
import org.gameloom.connect.game.user.bo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final GameRepository gameRepository;
    private final FeedbackMapper feedbackMapper;


    /**
     * @param request
     * @param connectedUser
     * @return
     */
    @Override
    public Integer save(FeedbackRequest request, Authentication connectedUser) {
        Game game = gameRepository.findById(request.gameId()).orElseThrow(() -> new EntityNotFoundException("No Game found with this Id = " + request.gameId()));
        if (!game.isShareable()) {
            throw new OperationNotPermittedException("you can not give a feedback to a non shareable game");
        }
        User user = (User) connectedUser.getPrincipal();
        if (Objects.equals(game.getOwner().getId(), user.getId())) {
            throw new OperationNotPermittedException("You can not give a feedback to your own game");
        }
        Feedback feedback = feedbackMapper.toFeedback(request);
        return feedbackRepository.save(feedback).getId();
    }

    /**
     * @param gameId
     * @param page
     * @param size
     * @param connectedUser
     * @return
     */
    @Override
    public PageResponse<FeedbackResponse> findAllFeedbacksByGame(Integer gameId, int page, int size, Authentication connectedUser) {
        Pageable pageable = PageRequest.of(page, size);
        User user = (User) connectedUser.getPrincipal();
        Page<Feedback> feedbacks = feedbackRepository.findAllByGameId(gameId, pageable);
        List<FeedbackResponse> feedbackResponses = feedbacks.stream().map(feedback -> feedbackMapper.toFeedbackResponse(feedback, user.getId())).toList();
        return new PageResponse<>(
                feedbackResponses,
                feedbacks.getNumber(),
                feedbacks.getSize(),
                feedbacks.getTotalElements(),
                feedbacks.getTotalPages(),
                feedbacks.isFirst(),
                feedbacks.isLast()
        );
    }
}
