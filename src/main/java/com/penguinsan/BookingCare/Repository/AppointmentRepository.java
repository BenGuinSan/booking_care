package com.penguinsan.BookingCare.Repository;

import com.penguinsan.BookingCare.Model.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    // lay lich hen theo id benh nhan xep theo ngay
    @Query("SELECT a FROM Appointment a WHERE a.patient.user_Id = ?1 ORDER BY a.appointment_Date DESC")
    List<Appointment> findByPatientId(int patientId);
    // Thêm phương thức tìm kiếm phân trang
    Page<Appointment> findAll(Pageable pageable);
}