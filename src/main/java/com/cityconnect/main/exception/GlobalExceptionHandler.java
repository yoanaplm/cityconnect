package com.cityconnect.main.exception;

import com.cityconnect.main.web.dto.RegisterRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ModelAndView handleUserAlreadyExists(UserAlreadyExistsException ex) {
        log.warn("Registration rejected: {}", ex.getMessage());
        ModelAndView modelAndView = new ModelAndView("register");
        modelAndView.addObject("registerRequest", new RegisterRequest());
        modelAndView.addObject("errorMessage", ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handleNotFound(NoResourceFoundException ex) {
        log.warn("Page not found: {}", ex.getResourcePath());
        ModelAndView modelAndView = new ModelAndView("error-page");
        modelAndView.addObject("message", "The page you are looking for does not exist.");
        return modelAndView;
    }
    @ExceptionHandler(AlreadyJoinedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ModelAndView handleAlreadyJoined(AlreadyJoinedException ex) {
        return buildErrorPage(ex.getMessage());
    }

    @ExceptionHandler(ParticipationUnavailableException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ModelAndView handleParticipationUnavailable(ParticipationUnavailableException ex) {
        return buildErrorPage(ex.getMessage());
    }

    @ExceptionHandler(ActivityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handleActivityNotFound(ActivityNotFoundException ex) {
        return buildErrorPage(ex.getMessage());
    }

    private ModelAndView buildErrorPage(String message) {
        log.warn("Request failed: {}", message);
        ModelAndView modelAndView = new ModelAndView("error-page");
        modelAndView.addObject("message", message);
        return modelAndView;
    }

}
