package com.penguinsan.BookingCare.Repository;

import com.penguinsan.BookingCare.Model.Appointment;
import com.penguinsan.BookingCare.Model.Schedules;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SchedulesRepository extends JpaRepository<Schedules, Integer> {

    @Query("SELECT s FROM Schedules s " +
            "WHERE s.user.role.role_Id = 2 " +
            "AND s.user.user_Id = ?1 " +
            "AND s.is_booked = false " +
            "AND (s.working_date > CURRENT_DATE OR (s.working_date = CURRENT_DATE AND s.start_time >= CURRENT_TIME)) " +
            "ORDER BY s.start_time ASC")
    List<Schedules> getSchedulesByDoctorIdIsBooked(int id);

    @Query("SELECT a FROM Schedules a WHERE a.user.email = ?1 ORDER BY a.working_date DESC")
    Page<Schedules> findByDoctorEmail(String doctorEmail, Pageable pageable);


}
