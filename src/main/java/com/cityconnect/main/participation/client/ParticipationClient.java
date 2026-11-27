package com.cityconnect.main.participation.client;


import com.cityconnect.main.participation.dto.ActivitySummaryResponse;
import com.cityconnect.main.participation.dto.ParticipationRequest;
import com.cityconnect.main.participation.dto.ParticipationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FeignClient(name = "participation-service", url = "${participation.service.url}")
public interface ParticipationClient {

    @PostMapping("/registrations")
    ParticipationResponse register(@RequestBody ParticipationRequest request);

    @DeleteMapping("/registrations/{id}")
    void cancel(@PathVariable("id") UUID id);

    @GetMapping("/activities/{activityId}/registrations")
    ActivitySummaryResponse getSummary(@PathVariable("activityId") UUID activityId);




}
