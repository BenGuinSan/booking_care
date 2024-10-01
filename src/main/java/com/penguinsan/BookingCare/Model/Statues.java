package com.penguinsan.BookingCare.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Statues {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int StatusId;

    private String Name;
}
