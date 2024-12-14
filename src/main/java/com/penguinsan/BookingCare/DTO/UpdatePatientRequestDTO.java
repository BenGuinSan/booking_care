package com.penguinsan.BookingCare.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class UpdatePatientRequestDTO {
    private String fullName;
    private String email;
    private String Phone;
    private String address;
    private boolean Gender;
    private Date DateOfBirth;
}
