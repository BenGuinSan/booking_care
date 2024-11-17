package com.penguinsan.BookingCare.Service;


import com.penguinsan.BookingCare.Model.Appointment;
import com.penguinsan.BookingCare.Model.Users;
import com.penguinsan.BookingCare.Repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    AppointmentRepository appointmentRepository;

    // lay lich hen theo id benh nhan
    public List<Appointment> getAppointmentByPatientId(int patientId)
    {
        return appointmentRepository.findByPatientId(patientId);
    }
    // lay lich hen theo id lich hen
    public Appointment getAppointmentById(int appointmentId) {
        return appointmentRepository.findById(appointmentId).orElse(null);
    }

    //them lich hen
    public Appointment addAppointment(Appointment appointment)
    {
        appointment = appointmentRepository.save(appointment);
        return appointment;
    }

    //lay tat ca lich kham
    public List<Appointment> getAllAppointment()
    {
        return appointmentRepository.findAll();
    }

    public Page<Appointment> getAppointments(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return appointmentRepository.findAll(pageable);
    }
}
