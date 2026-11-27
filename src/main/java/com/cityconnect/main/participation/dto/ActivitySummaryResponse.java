package com.cityconnect.main.participation.dto;

import java.util.UUID;

public class ActivitySummaryResponse {
    private UUID activityId;
    private long confirmed;
    private long waitlisted;

    public UUID getActivityId() {
        return activityId;
    }

    public void setActivityId(UUID activityId) {
        this.activityId = activityId;
    }

    public long getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(long confirmed) {
        this.confirmed = confirmed;
    }

    public long getWaitlisted() {
        return waitlisted;
    }

    public void setWaitlisted(long waitlisted) {
        this.waitlisted = waitlisted;
    }
}
