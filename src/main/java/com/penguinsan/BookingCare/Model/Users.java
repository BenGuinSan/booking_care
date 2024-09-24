package com.penguinsan.BookingCare.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;


@Entity
@Data
public class Users {

    public enum Roles {
        ADMIN,
        DOCTOR,
        PATIENT
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int User_Id;
    private String UserName;
    private String Email;
    private String Password;
    private Roles Role;
    private Date CreateAt;
    private Date UpdateAt;
    private Date DeleteAt;
}
