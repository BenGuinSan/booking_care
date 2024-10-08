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
    private int User_Id;
    private String FullName;
    private String Email;
    private String Password;
    private String Phone;
    private String Degree;
    private float Booking_Fee;
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
    @JoinColumn(name = "clinic_id")
    public Clinics clinic;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Schedules> schedules;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Appointment> patientAppointments;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<Appointment> doctorAppointments;


}
