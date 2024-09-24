package com.penguinsan.BookingCare.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Entity
@Data
public class Schedules {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Schedule_Id;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctors DoctorId;

    private Date Appointment_date;

    private Time Start_time;

    private Time End_Time;

    private boolean is_booked;

    private Date CreateAt;

    private Date UpdateAt;

    private Date DeleteAt;
}
