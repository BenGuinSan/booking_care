package com.penguinsan.BookingCare.DTO;

import com.penguinsan.BookingCare.Model.Clinics;
import com.penguinsan.BookingCare.Model.Roles;
import com.penguinsan.BookingCare.Model.Specializations;
import lombok.Data;

import java.time.Year;
import java.util.Date;

@Data
public class DoctorDTO {
    private int User_Id;
    private String FullName;
    private String Password;
    private String email;
    private String Phone;
    private String Degree;
    private float Booking_Fee;
    private String Image;
    private boolean Gender;
    private String Address;
    private Date DateOfBirth;
    private Year Experience;
    private Roles Role_Id;
    private Specializations Specialization_Id;
    private Clinics Clinic_Id;

}
