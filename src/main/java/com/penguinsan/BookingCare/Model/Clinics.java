package com.penguinsan.BookingCare.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Clinics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ClinicId;

    private String Name;

    private String Address;

    private String Phone;

    private String Image;

    private String Description;

    private String Introduction;

    private Date CreateAt;

    private Date UpdateAt;

    private Date DeleteAt;
}
