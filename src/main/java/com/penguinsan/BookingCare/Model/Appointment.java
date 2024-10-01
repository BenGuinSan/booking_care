package com.penguinsan.BookingCare.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Appointment_Id;

//    @ManyToOne
//    @JoinColumn(name = "patient_id")
//    private Patients patientId;
//
//    @ManyToOne
//    @JoinColumn(name = "doctor_id")
//    private Doctors DoctorId;
//
    @ManyToOne
    @JoinColumn(name = "status_id")
    private Statues status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    private Date Appointment_Date;

    private Time Time_Slot;

    private String Reason;
}
