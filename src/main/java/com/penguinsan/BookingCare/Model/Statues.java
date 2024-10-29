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
public class Statues {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int status_Id;

    private String name;

}
