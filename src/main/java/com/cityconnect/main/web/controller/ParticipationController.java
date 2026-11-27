package com.cityconnect.main.web.controller;

import com.cityconnect.main.participation.service.ParticipationService;
import com.cityconnect.main.user.model.AuthenticationDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
@RequestMapping("/my-activities")
public class ParticipationController {

    private final ParticipationService participationService;

    public ParticipationController( ParticipationService participationService) {

        this.participationService = participationService;
    }
    @GetMapping
    public ModelAndView getMyActivitiesPage(@AuthenticationPrincipal AuthenticationDetails details) {
        ModelAndView modelAndView = new ModelAndView("my-activities");
        modelAndView.addObject("participations", participationService.getMyParticipations(details.getUserId()));
        return modelAndView;
    }

    @PostMapping("/{registrationId}/cancel")
    public ModelAndView cancel(@PathVariable UUID registrationId, @AuthenticationPrincipal AuthenticationDetails details){
        participationService.cancel(details.getUserId(),registrationId);
        return new ModelAndView("redirect:/my-activities");
    }


}
