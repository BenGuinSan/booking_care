package com.penguinsan.BookingCare.DTO.AuthDTO;

import lombok.Data;

import java.time.Year;
import java.util.Date;

@Data
public class LoginDTO {
    private String email;
    private String Password;
}

