package com.penguinsan.BookingCare.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Year;
import java.util.Date;
import java.util.List;


@Entity
@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_Id;
    private String fullName;
    private String email;
    private String Password;
    private String Phone;
    private String Degree;
    private float Booking_Fee;
    private String address;
    private String Image;
    private boolean Available;
    private boolean Gender;

    private Date DateOfBirth;
    private Year Experience;

    @ManyToOne
    @JoinColumn(name = "Role_Id")
    public Roles role;

    @ManyToOne
    @JoinColumn(name = "Specialization_Id")
    public Specializations specialization;

    @ManyToOne
    @JoinColumn(name = "Clinic_Id")
    public Clinics clinic_Id;
}
