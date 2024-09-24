package com.penguinsan.BookingCare.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Patients {
    private enum Gender{
        MALE,
        FEMALE,
        OTHER
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int PatientId;

    @OneToOne
    private Users User_Id;

    private String FullName;

    private Date DateOfBirth;

    private Gender Gender;

    private String Phone;

    private Date CreateAt;

    private Date UpdateAt;

    private Date DeleteAt;
}
