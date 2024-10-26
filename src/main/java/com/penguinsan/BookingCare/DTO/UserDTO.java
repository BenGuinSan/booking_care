package com.penguinsan.BookingCare.DTO;

import com.penguinsan.BookingCare.Model.Clinics;
import com.penguinsan.BookingCare.Model.Roles;
import com.penguinsan.BookingCare.Model.Specializations;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Year;
import java.util.Date;

@Data
public class UserDTO {
    private int User_Id;
    private String full_name;
    private String Email;
    private String Password;
    private String Phone;
    private float Booking_Fee;
    private String Address;
    private String Image;
    private boolean Available;
    private boolean Gender;
    private Date DateOfBirth;
    private Year Experience;
}
