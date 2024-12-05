package com.penguinsan.BookingCare.Repository;


import com.penguinsan.BookingCare.Model.Users;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomUserRepository {
    List<Users> findAllDoctor();

    List<Users> findAllPatient();

    List<Users> findDoctorExperience();
}
