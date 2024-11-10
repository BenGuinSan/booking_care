package com.penguinsan.BookingCare.Repository;

import com.penguinsan.BookingCare.Model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    // lay lich hen theo id benh nhan
    @Query("SELECT a FROM Appointment a WHERE a.patient.user_Id = ?1")
    List<Appointment> findByPatientId(int patientId);
}
