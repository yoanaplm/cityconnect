package com.cityconnect.main.participation.service;

import com.cityconnect.main.activity.model.Activity;
import com.cityconnect.main.activity.service.ActivityService;
import com.cityconnect.main.exception.AlreadyJoinedException;
import com.cityconnect.main.exception.ParticipationUnavailableException;
import com.cityconnect.main.participation.client.ParticipationClient;
import com.cityconnect.main.participation.dto.ParticipationRequest;
import com.cityconnect.main.participation.dto.ParticipationResponse;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ParticipationService {

    private static final Logger log = LoggerFactory.getLogger(ParticipationService.class);

    private final ParticipationClient participationClient;
    private final ActivityService activityService;

    public ParticipationService(ParticipationClient participationClient, ActivityService activityService) {
        this.participationClient = participationClient;
        this.activityService = activityService;
    }

    public ParticipationResponse join(UUID userId, UUID activityId) {
        Activity activity = activityService.getById(activityId);

        ParticipationRequest request = new ParticipationRequest();
        request.setUserId(userId);
        request.setActivityId(activity.getId());
        request.setCapacity(activity.getCapacity());
        try {
            ParticipationResponse response = participationClient.register(request);
            log.info("User {} joined activity {} with status {}", userId, activityId, response.getStatus());
            return response;
        } catch (FeignException.Conflict e) {
            log.warn("User {} already joined activity {}", userId, activityId);
            throw new AlreadyJoinedException("You have already joined this activity.");
        } catch (FeignException e) {
            log.error("Participation service call failed: {}", e.getMessage());
            throw new ParticipationUnavailableException("Sign-ups are temporarily unavailable. Please try again later.");
        }
    }
}
