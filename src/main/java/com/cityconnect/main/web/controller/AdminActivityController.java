package com.cityconnect.main.web.controller;

import com.cityconnect.main.activity.service.ActivityService;
import com.cityconnect.main.web.dto.ActivityRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/activities")
public class AdminActivityController {
    private final ActivityService activityService;

    public AdminActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping
    public ModelAndView getActivitiesPage(){
        ModelAndView modelAndView=new ModelAndView("admin/activities");
        modelAndView.addObject("activities",activityService.getAllUpcoming());
        return modelAndView;

    }
    @GetMapping("/new")
    public ModelAndView getCreatePage() {
        ModelAndView modelAndView = new ModelAndView("admin/activity-form");
        modelAndView.addObject("activityRequest", new ActivityRequest());
        return modelAndView;
    }
    @PostMapping
    public ModelAndView create(@Valid ActivityRequest activityRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return new ModelAndView("admin/activity-form");
        }
        activityService.create(activityRequest);
        return new ModelAndView("redirect:/admin/activities");

    }

}
