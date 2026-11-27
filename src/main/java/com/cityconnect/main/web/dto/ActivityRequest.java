package com.cityconnect.main.web.dto;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class ActivityRequest {

    @NotBlank(message = "Title is required.")
    @Size(max = 100, message = "The title can have up to  100 characters.")
    private String title;


    @NotBlank(message = "Description is required.")
    @Size(max=1000,message = "The description can have up to 1000 characters.")
    private String description;


    @NotBlank(message = "City is required.")
    private String city;


    @NotNull(message = "Start time is required.")
    @Future(message = "The start time must be in the future.")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime startsAt;


    @NotNull(message = "Capacity is required.")
    @Min(value = 1, message = "The capacity must be at least 1.")
    @Max(value = 500, message = "The capacity can be up to 500.")
    private Integer capacity;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDateTime getStartsAt() {
        return startsAt;
    }

    public void setStartsAt(LocalDateTime startsAt) {
        this.startsAt = startsAt;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
}
