package com.cityconnect.main.web.controller;

import com.cityconnect.main.activity.service.ActivityService;
import com.cityconnect.main.participation.service.ParticipationService;
import com.cityconnect.main.user.model.AuthenticationDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
@RequestMapping("/activities")
public class ActivityController {

    private final ActivityService activityService;
    private final ParticipationService participationService;

    public ActivityController(ActivityService activityService, ParticipationService participationService) {
        this.activityService = activityService;
        this.participationService = participationService;
    }

    @GetMapping
    public ModelAndView getActivitiesPage(@AuthenticationPrincipal AuthenticationDetails details, Model model){
        ModelAndView modelAndView = new ModelAndView("activities");
        modelAndView.addObject("activities", activityService.getUpcomingForUser(details.getUserId()));
        return modelAndView;
    }

    @PostMapping("/{id}/join")
    public ModelAndView join(@PathVariable UUID id, @AuthenticationPrincipal AuthenticationDetails details){
        participationService.join(details.getUserId(), id);
        return new ModelAndView("redirect:/my-activities");



    }
}
