package com.penguinsan.BookingCare.Service;


import com.penguinsan.BookingCare.Model.Appointment;
import com.penguinsan.BookingCare.Model.Users;
import com.penguinsan.BookingCare.Repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    AppointmentRepository appointmentRepository;

    // lay lich hen theo id benh nhan
    public List<Appointment> getAppointmentByPatientId(int patientId , int skip, int size)
    {
        Pageable pageable = PageRequest.of(skip/size, size);
        return appointmentRepository.findByPatientId(patientId, pageable).getContent();
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

    // dem so lich hen theo id benh nhan
    public int countByPatientId(int patientId)
    {
        return appointmentRepository.countByPatientId(patientId);
    }

    // kiem tra benh nhan co dat trung lich khong
    public int existsByPatientIdAndAppointmentDateAndStartTime(int patientId, Date appointmentDate, Time startTime)
    {
        return appointmentRepository.existsByPatientIdAndAppointmentDateAndStartTime(patientId, appointmentDate, startTime);
    }


    // Cua ong (Vu)
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
