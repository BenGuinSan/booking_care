package com.penguinsan.BookingCare.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class UpdateDoctorRequestDTO {
    private String FullName;

    private float Booking_Fee;

    private String Specialization;
}
