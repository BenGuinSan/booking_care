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
    private String full_name;
    private String email;
    private String password;
    private String phone;
    private String degree;
    private float bookingFee;
    private String address;
    private String image;
    private boolean available;
    private boolean gender;

    private Date DateOfBirth;
    private Year experience;

    @ManyToOne
    @JoinColumn(name = "Role_Id")
    public Roles role;

    @ManyToOne
    @JoinColumn(name = "Specialization_Id")
    public Specializations specialization;

    @ManyToOne
    @JoinColumn(name = "Clinic_Id")
    public Clinics clinic;
}
