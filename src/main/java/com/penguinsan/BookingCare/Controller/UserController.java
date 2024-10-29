package com.penguinsan.BookingCare.Controller;

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
    @CrossOrigin(origins = "http://localhost:5173")
    public List<UserDTO> getAllUser()
    {
        return UserService.getAllUser();
    }

    // Lấy ra các user là doctor
    @GetMapping("/user/doctor")
    @CrossOrigin(origins = "http://localhost:5173")
    public List<Users> getAllDoctor()
    {
        return UserService.getAllDoctor();
    }

    // Lấy ra các user là patient
    @GetMapping("/user/patient")
    @CrossOrigin(origins = "http://localhost:5173")
    public List<Users> getAllPatient()
    {
        return UserService.getAllPatient();
    }

    // Lấy user theo id
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("user/{id}")
    public Users getUserById(@PathVariable int id) {
        return UserService.findUserById(id);
    }

}
