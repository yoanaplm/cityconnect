package com.cityconnect.main.activity.repository;

import com.cityconnect.main.activity.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ActivityRepository extends JpaRepository<Activity, UUID> {


    @Query("SELECT a FROM Activity a WHERE LOWER(a.city) = LOWER(:city) AND a.startsAt > :now ORDER BY a.startsAt ASC")
    List<Activity> findUpcomingByCity(@Param("city") String city, @Param("now") LocalDateTime now);

    @Query("SELECT a FROM Activity a WHERE a.startsAt > :now ORDER BY a.startsAt ASC")
    List<Activity> findAllUpcoming(@Param("now") LocalDateTime now);
}
