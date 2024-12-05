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

    public enum Payment_method{
        ONLINE,
        CASH
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int payment_Id;

    private long amount;

    private Date payment_Date;

    @Enumerated(EnumType.STRING)
    private Payment_method payment_method;
}
