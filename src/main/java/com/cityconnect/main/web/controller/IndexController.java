package com.cityconnect.main.web.controller;


import com.cityconnect.main.user.model.AuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.security.Principal;

@Controller
public class IndexController {


    @GetMapping("/")
    public ModelAndView getHomePage(@AuthenticationPrincipal AuthenticationDetails details){

        ModelAndView modelAndView = new ModelAndView("index");
        if(details!=null){
            modelAndView.addObject("username",details.getUsername());

        }
        return modelAndView;
    }
    @GetMapping("/login")
    public ModelAndView getLoginPage() {
        return new ModelAndView("login");
    }

}

