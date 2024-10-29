package com.penguinsan.BookingCare.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payments {

    private enum Payment_method{
        ONLINE,
        CASH
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int payment_Id;

    @OneToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

    private float amount;

    private Date payment_Date;

    private Payment_method payment_method;
}
