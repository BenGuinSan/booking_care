package com.penguinsan.BookingCare.Controller;

import com.penguinsan.BookingCare.DTO.DoctorDTO;
import com.penguinsan.BookingCare.DTO.PatientDTO;
import com.penguinsan.BookingCare.DTO.UserDTO;
import com.penguinsan.BookingCare.Model.Users;
import com.penguinsan.BookingCare.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService UserService;

    // Lấy toàn bộ danh sách User
    @GetMapping("/user")
    public List<UserDTO> getAllUser()
    {
        return UserService.getAllUser();
    }

    // Lấy ra các user là doctor
    @GetMapping("/user/doctor")
    public List<DoctorDTO> getAllDoctor()
    {
        return UserService.getAllDoctor();
    }

    // Lấy ra các user là patient
    @GetMapping("/user/patient")
    public List<PatientDTO> getAllPatient()
    {
        return UserService.getAllPatient();
    }

}
