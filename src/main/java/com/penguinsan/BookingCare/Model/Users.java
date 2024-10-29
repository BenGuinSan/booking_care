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
    private String password;
    private String phone;
    private String image;
    private String degree;
    private float booking_Fee;
    private boolean available;
    private boolean gender;
    private Date dateOfBirth;
    private Year experience;


    @ManyToOne
    @JoinColumn(name = "Role_Id")
    public Roles Role_Id;

    @ManyToOne
    @JoinColumn(name = "Specialization_Id")
    public Specializations Specialization_Id;

    @ManyToOne

    @JoinColumn(name = "clinic_id")
    public Clinics clinic;


}
