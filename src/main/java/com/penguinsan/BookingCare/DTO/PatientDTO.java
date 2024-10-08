package com.penguinsan.BookingCare.DTO;

import lombok.Data;

import java.time.Year;
import java.util.Date;

@Data
public class PatientDTO {
    private int User_Id;
    private String FullName;
    private String Email;
    private String Phone;
    private boolean Gender;
    private Date DateOfBirth;
}
