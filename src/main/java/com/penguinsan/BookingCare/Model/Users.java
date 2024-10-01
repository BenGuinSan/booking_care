package com.penguinsan.BookingCare.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;


@Entity
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int User_Id;
    private String FullName;
    private String Email;
    private String Password;
    private String Phone;
    private float Booking_Fee;
    private float Rating;
    private boolean Available;
    private boolean Gender;
    private Date DateOfBirth;

    @ManyToOne
    @JoinColumn(name = "Role_Id")
    public Roles Role_Id;

    @ManyToOne
    @JoinColumn(name = "Specialization_Id")
    public Specializations Specialization_Id;

    @ManyToOne
    @JoinColumn(name = "Clinic_Id")
    public Clinics Clinic_Id;
}
