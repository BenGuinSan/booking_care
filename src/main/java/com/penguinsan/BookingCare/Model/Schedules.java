package com.penguinsan.BookingCare.Model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.util.Date;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Schedules {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Schedule_Id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users User;

    private Date Appointment_date;

    private Time Start_time;

    private Time End_Time;

    private boolean is_booked;

}
