package com.cityconnect.main.participation.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class ParticipationResponse {
    private UUID id;
    private UUID userId;
    private UUID activityId;
    private ParticipationStatus status;
    private LocalDateTime registeredAt;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getActivityId() {
        return activityId;
    }

    public void setActivityId(UUID activityId) {
        this.activityId = activityId;
    }

    public ParticipationStatus getStatus() {
        return status;
    }

    public void setStatus(ParticipationStatus status) {
        this.status = status;
    }

    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(LocalDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }
}
