package com.penguinsan.BookingCare.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Admin_Id;

    @OneToOne
    private Users User_Id;

    private String FullName;

    private Date CreateAt;

    private Date UpdateAt;

    private Date DeleteAt;
}
