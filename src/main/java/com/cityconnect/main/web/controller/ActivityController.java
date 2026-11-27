package com.cityconnect.main.web.controller;

import com.cityconnect.main.activity.service.ActivityService;
import com.cityconnect.main.user.model.AuthenticationDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/activities")
public class ActivityController {

    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping
    public ModelAndView getActivitiesPage(@AuthenticationPrincipal AuthenticationDetails details, Model model){
        ModelAndView modelAndView = new ModelAndView("activities");
        modelAndView.addObject("activities", activityService.getUpcomingForUser(details.getUserId()));
        return modelAndView;
    }
}
