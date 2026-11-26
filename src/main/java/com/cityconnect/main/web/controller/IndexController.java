package com.cityconnect.main.web.controller;


import com.cityconnect.main.user.model.AuthenticationDetails;
import com.cityconnect.main.user.service.UserService;
import com.cityconnect.main.web.dto.RegisterRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.security.Principal;

@Controller
public class IndexController {


    private final UserService userService;

    public IndexController(UserService userService) {
        this.userService = userService;
    }

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


    @GetMapping("/register")
    public ModelAndView getRegisterPage(){
        ModelAndView modelAndView = new ModelAndView("register");
        modelAndView.addObject("registerRequest", new RegisterRequest());
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView register(@Valid RegisterRequest registerRequest, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return new ModelAndView("register");
        }
        userService.register(registerRequest);
        return new ModelAndView("redirect:/login");
    }

}

