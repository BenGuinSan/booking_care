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
public class Specializations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int specialization_Id;

    private String name;

    private String description;

    private String image;

    @OneToMany(mappedBy = "specialization")
    private List<Users> users;

}
