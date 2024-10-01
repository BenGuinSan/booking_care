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
    private int Specialization_Id;

    private String Name;

    private String Description;

    private String Image;

//    @OneToMany(mappedBy = "SpecializationId")
//    private List<Doctors> doctors;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private Users user;
}
