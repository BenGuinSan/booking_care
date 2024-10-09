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
    private String degree;
    private float booking_Fee;
    private boolean available;
    private boolean gender;
    private Date dateOfBirth;
    private Year experience;


    @ManyToOne
    @JoinColumn(name = "Role_Id")
    public Roles role;

    @ManyToOne
    @JoinColumn(name = "Specialization_Id")
    public Specializations specialization;

    @ManyToOne
    @JoinColumn(name = "clinic_id")
    public Clinics clinic;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<Schedules> schedules;
//
//    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
//    private List<Appointment> patientAppointments;
//
//    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
//    private List<Appointment> doctorAppointments;


}
