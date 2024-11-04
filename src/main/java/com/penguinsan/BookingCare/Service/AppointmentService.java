package com.penguinsan.BookingCare.Service;

import com.penguinsan.BookingCare.Model.Appointment;
import com.penguinsan.BookingCare.Repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public abstract class AppointmentService {
    //get all


    @Autowired
    private AppointmentRepository appointmentRepository;

    // Lấy danh sách appointment theo doctor
    public List<Appointment> getAppointmentsByDoctorId(int doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }

    // Update status của appointment
    public boolean updateAppointmentStatus(int appointmentId, int statusId) {
            int updated = appointmentRepository.updateAppointmentStatus(appointmentId, statusId);
            return updated > 0;

    }

    // Delete appointment
    public boolean deleteAppointment(int appointmentId) {
            int deleted = appointmentRepository.deleteAppointment(appointmentId);
            return deleted > 0;
    }

}
