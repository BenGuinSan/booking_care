package com.penguinsan.BookingCare.DTO;

import lombok.Data;

import java.sql.Time;
import java.time.LocalDate;

@Data
public class SchedulesRequestDTO {
    private String working_date;

    private String start_time;

    private String end_time;
}
