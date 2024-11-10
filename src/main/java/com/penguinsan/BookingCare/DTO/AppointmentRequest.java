package com.penguinsan.BookingCare.DTO;



import com.penguinsan.BookingCare.Model.Schedules;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;

public class AppointmentRequest {

    private Doctor doctor;
    private Patient patient;
    private Time startTime; // Trường startTime với kiểu Time
    private Date appointmentDate; // Trường appointmentDate với kiểu Date
    private Schedules schedule;




    // Constructor mặc định
    public AppointmentRequest() {}

    // Constructor với tham số
    public AppointmentRequest(Doctor doctor, Patient patient, Time startTime, Date appointmentDate, Schedules schedule){
        this.doctor = doctor;
        this.patient = patient;
        this.startTime = startTime;
        this.appointmentDate = appointmentDate;
        this.schedule = schedule;

    }



    // Getter và Setter cho doctor
    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    // Getter và Setter cho patient
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    // Getter và Setter cho startTime
    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    // Getter và Setter cho appointmentDate
    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    // Getter và Setter cho schedule
    public Schedules getSchedule() {
        return schedule;
    }
    public void setSchedule(Schedules schedule) {
        this.schedule = schedule;
    }

    // Lớp con Doctor
    public static class Doctor {
        private int userId;

        // Getter và Setter cho userId
        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        // Constructor cho Doctor
        public Doctor() {}

        public Doctor(int userId) {
            this.userId = userId;
        }
    }

    // Lớp con Patient
    public static class Patient {
        private int userId;

        // Getter và Setter cho userId
        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        // Constructor cho Patient
        public Patient() {}

        public Patient(int userId) {
            this.userId = userId;
        }
    }

    public static class Schedule {
        private int scheduleId;

        // Getter và Setter cho scheduleId
        public int getScheduleId() {
            return scheduleId;
        }

        public void setScheduleId(int scheduleId) {
            this.scheduleId = scheduleId;
        }

        // Constructor cho Schedule
        public Schedule() {}

        public Schedule(int scheduleId) {
            this.scheduleId = scheduleId;
        }
    }


}
