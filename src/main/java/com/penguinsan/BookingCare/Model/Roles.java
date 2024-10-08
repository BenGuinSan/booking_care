package com.penguinsan.BookingCare.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int role_Id;

    private String name;

    @OneToMany(mappedBy = "role")
    private List<Users> users;

}
