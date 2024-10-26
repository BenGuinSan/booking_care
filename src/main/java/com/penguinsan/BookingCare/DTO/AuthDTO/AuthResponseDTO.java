package com.penguinsan.BookingCare.DTO.AuthDTO;

import lombok.Data;

import java.time.Year;
import java.util.Date;

@Data
public class AuthResponseDTO {
    private String accessToken;
    private String tokenType = "Bearer ";

    public AuthResponseDTO(String accessToken) {
        this.accessToken = accessToken;
    }
}
