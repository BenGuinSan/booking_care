package com.penguinsan.BookingCare.DTO;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class VnPayRepesDTO implements Serializable {
    private String status;
    private String message;
    private String URL;
}
