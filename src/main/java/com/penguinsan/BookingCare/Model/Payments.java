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
        CREDIT_CARD,
        PAYPAL,
        CASH,
        BANK_TRANSFER
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Payment_Id;

    @OneToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

    private float Amount;

    private Date Payment_Date;

    private Payment_method Payment_method;
}
