package com.cityconnect.main.activity.service;

import com.cityconnect.main.activity.model.Activity;
import com.cityconnect.main.activity.repository.ActivityRepository;
import com.cityconnect.main.web.dto.ActivityRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ActivityService {

    private static final Logger log = LoggerFactory.getLogger(ActivityService.class);

    private final ActivityRepository activityRepository;

    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Transactional
    public Activity create(ActivityRequest request) {
        Activity activity = new Activity();
        activity.setTitle(request.getTitle());
        activity.setDescription(request.getDescription());
        activity.setCity(request.getCity());
        activity.setStartsAt(request.getStartsAt());
        activity.setCapacity(request.getCapacity());


        Activity saved = activityRepository.save(activity);
        log.info("Created activity {} in {}", saved.getTitle(), saved.getCity());
        return saved;
    }

    @Transactional(readOnly=true)
    public List<Activity> getAllUpcoming(){
        return activityRepository.findAllUpcoming(LocalDateTime.now());
    }

    @Transactional(readOnly = true)
    public List<Activity> getUpcomingForCity(String city) {
        return activityRepository.findUpcomingByCity(city, LocalDateTime.now());

    }
}


