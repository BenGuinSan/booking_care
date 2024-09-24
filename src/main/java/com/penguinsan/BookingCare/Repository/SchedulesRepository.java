package com.penguinsan.BookingCare.Repository;

import com.penguinsan.BookingCare.Model.Schedules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchedulesRepository extends JpaRepository<Schedules, Integer> {
}
