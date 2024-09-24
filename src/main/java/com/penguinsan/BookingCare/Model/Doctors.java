package com.penguinsan.BookingCare.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Doctors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Doctor_Id;

    @OneToOne
    private Users User_Id;

    private String FullName;

    @ManyToOne
    @JoinColumn(name = "specialization_id")
    private Specializations SpecializationId;

    private String Phone;

    private float Booking_Fee;

    private float Rating;

    private boolean Available;

    private Date CreateAt;

    private Date UpdateAt;

    private Date DeleteAt;
}
