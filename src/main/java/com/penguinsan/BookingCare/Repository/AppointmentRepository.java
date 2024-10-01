package com.penguinsan.BookingCare.Repository;

import com.penguinsan.BookingCare.Model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    // Truy vấn lấy ra toàn bộ danh sách Appointments
}
