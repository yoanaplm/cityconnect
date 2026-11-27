package com.cityconnect.main.participation.dto;

import com.cityconnect.main.participation.service.ParticipationService;

import java.time.LocalDateTime;
import java.util.UUID;

public class MyParticipation {

    private final UUID registrationId;
    private final String activityTitle;
    private final String city;
    private final LocalDateTime startsAt;
    private final ParticipationStatus status;

    public MyParticipation(UUID registrationId, String activityTitle, String city, LocalDateTime startsAt, ParticipationStatus status) {
        this.registrationId = registrationId;
        this.activityTitle = activityTitle;
        this.city = city;
        this.startsAt = startsAt;
        this.status = status;
    }

    public UUID getRegistrationId() {
        return registrationId;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public String getCity() {
        return city;
    }

    public LocalDateTime getStartsAt() {
        return startsAt;
    }

    public ParticipationStatus getStatus() {
        return status;
    }
}
