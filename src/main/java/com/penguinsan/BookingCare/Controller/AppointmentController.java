package com.penguinsan.BookingCare.Controller;

import com.penguinsan.BookingCare.Model.Appointment;
import com.penguinsan.BookingCare.Service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    // API lấy danh sách appointment theo doctor
    @GetMapping("/doctor/{doctorId}")
    public void getAppointmentsByDoctor(@PathVariable int doctorId) {
        appointmentService.getAppointmentsByDoctorId(doctorId);
    }

    // API update status của appointment
    @PutMapping("/{appointmentId}/status/{statusId}")
    public void updateAppointmentStatus(
            @PathVariable int appointmentId,
            @PathVariable int statusId) {
        appointmentService.updateAppointmentStatus(appointmentId, statusId);
    }

    // API xóa appointment
    @DeleteMapping("/{appointmentId}")
    public void deleteAppointment(@PathVariable int appointmentId) {
        appointmentService.deleteAppointment(appointmentId);
    }
}
