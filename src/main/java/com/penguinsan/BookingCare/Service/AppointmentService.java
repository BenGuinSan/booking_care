package com.penguinsan.BookingCare.Service;


import com.penguinsan.BookingCare.Model.Appointment;
import com.penguinsan.BookingCare.Model.Users;
import com.penguinsan.BookingCare.Repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    //them lich hen
    public void addAppointment(Appointment appointment)
    {
        appointmentRepository.save(appointment);
    }

    //lay tat ca lich kham
    public List<Appointment> getAllAppointment()
    {
        return appointmentRepository.findAll();
    }
}
