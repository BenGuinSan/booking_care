package com.penguinsan.BookingCare.DTO;

import lombok.Data;

import java.time.Year;
import java.util.Date;

@Data
public class PatientDTO {
    private String full_name;
    private String Email;
    private String Phone;
    private String Image;
    private String Address;
    private boolean Gender;
    private Date DateOfBirth;
}
