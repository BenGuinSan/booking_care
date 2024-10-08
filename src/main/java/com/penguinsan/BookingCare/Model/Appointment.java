package com.penguinsan.BookingCare.Model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int appointment_Id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Users patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Users doctor;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Statues status;

    private Date appointment_Date;

    private Time time_Slot;

    private String reason;
}
