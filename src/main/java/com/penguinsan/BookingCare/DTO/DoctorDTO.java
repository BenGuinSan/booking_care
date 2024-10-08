package com.penguinsan.BookingCare.DTO;

import lombok.Data;

import java.time.Year;
import java.util.Date;

@Data
public class DoctorDTO {
    private int User_Id;
    private String FullName;
    private String Email;
    private String Phone;
    private float Booking_Fee;
    private boolean Gender;
    private Date DateOfBirth;
    private Year Experience;
}
