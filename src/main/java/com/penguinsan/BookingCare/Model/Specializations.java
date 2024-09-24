package com.penguinsan.BookingCare.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Specializations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int SpecializationId;

    private String Name;

    private String Description;

    private String Image;

    private Date CreateAt;

    private Date UpdateAt;

    private Date DeleteAt;

    @OneToMany(mappedBy = "SpecializationId")
    private List<Doctors> doctors;
}
