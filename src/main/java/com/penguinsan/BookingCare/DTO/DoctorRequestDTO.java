package com.penguinsan.BookingCare.DTO;

import com.penguinsan.BookingCare.Model.Clinics;
import com.penguinsan.BookingCare.Model.Roles;
import com.penguinsan.BookingCare.Model.Specializations;
import jakarta.annotation.Nullable;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.MultipartFilter;

import java.time.Year;
import java.util.Date;

@Data
public class DoctorRequestDTO {
    private String FullName;
    private String Password;
    private String email;
    private String Degree;
    private float Booking_Fee;
    private String Address;
    private int Experience;
    private Date DateOfBirth;
    private String Specialization;
    private String Description;
    private String imageUrl;

    private boolean Gender;
    private String Phone;

}
