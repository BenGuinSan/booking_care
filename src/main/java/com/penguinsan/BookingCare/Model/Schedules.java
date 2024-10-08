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
    private Users user;

    private Date appointment_date;

    private Time start_time;

    private Time end_Time;

    private boolean is_booked;

}
