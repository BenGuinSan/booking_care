package com.penguinsan.BookingCare.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Clinics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int clinicId;

    private String name;

    private String address;

    private String phone;

    private String image;

    private String description;

    private String introduction;

    @OneToMany(mappedBy = "clinic")
    private List<Users> users;

}
