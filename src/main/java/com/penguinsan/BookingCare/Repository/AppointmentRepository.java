package com.penguinsan.BookingCare.Repository;

import com.penguinsan.BookingCare.Model.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    // lay lich hen theo id benh nhan xep theo ngay
    @Query("SELECT a FROM Appointment a WHERE a.patient.user_Id = ?1 ORDER BY a.appointment_Date DESC")
    Page<Appointment> findByPatientId(int patientId, Pageable pageable);

    // đếm số lịch hẹn theo id benh nhan
    @Query("SELECT COUNT(a) FROM Appointment a WHERE a.patient.user_Id = ?1")
    int countByPatientId(int patientId);

    // kiểm tra benh nhan có dat trung lịch không
    @Query("SELECT COUNT(a) FROM Appointment a WHERE a.patient.user_Id = ?1 AND a.appointment_Date = ?2 AND a.start_time = ?3 and a.status.status_Id = 1 ")
    int existsByPatientIdAndAppointmentDateAndStartTime(int patientId, Date appointmentDate, Time startTime);

}