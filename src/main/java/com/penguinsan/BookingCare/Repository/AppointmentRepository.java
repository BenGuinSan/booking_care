package com.penguinsan.BookingCare.Repository;

import com.penguinsan.BookingCare.Model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    // Truy vấn lấy ra toàn bộ danh sách Appointments

    /// Lấy danh sách appointment theo doctor id
    @Query("SELECT a FROM Appointment a WHERE a.doctor.user_Id = :doctorId")
    List<Appointment> findByDoctorId(@Param("doctorId") int doctorId);

    // Update status của appointment
    @Modifying
    @Transactional
    @Query("UPDATE Appointment a SET a.status.status_Id = :statusId WHERE a.appointment_Id = :appointmentId")
    int updateAppointmentStatus(@Param("appointmentId") int appointmentId, @Param("statusId") int statusId);

    // Delete appointment
    @Modifying
    @Transactional
    @Query("DELETE FROM Appointment a WHERE a.appointment_Id = :appointmentId")
    int deleteAppointment(@Param("appointmentId") int appointmentId);
}
