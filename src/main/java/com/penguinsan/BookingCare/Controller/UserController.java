package com.penguinsan.BookingCare.Controller;

import com.penguinsan.BookingCare.DTO.DoctorDTO;
import com.penguinsan.BookingCare.DTO.PatientDTO;
import com.penguinsan.BookingCare.DTO.UserDTO;
import com.penguinsan.BookingCare.Model.Users;
import com.penguinsan.BookingCare.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    // Lấy toàn bộ danh sách User
    @GetMapping("/user")
    @CrossOrigin(origins = "http://localhost:5173")

    public List<UserDTO> getAllUser()
    {
        return userService.getAllUser();
    }

    // Lấy ra các user là doctor

    @GetMapping("/user/doctor")
    @CrossOrigin(origins = "http://localhost:5173")
    public List<DoctorDTO> getAllDoctor()

    {
        return userService.getAllDoctor();
    }

    // Tạo mới một doctor (ADMIN)
    @PostMapping("doctor/add")
    public void addDoctor(@RequestBody DoctorDTO doctorDTO){
        userService.addDoctor(doctorDTO);
    }

    // Xóa một doctor (ADMIN)
    @DeleteMapping("doctor/delete/{doctorId}")
    public void deleteDoctor(@PathVariable int doctorId){
        userService.deleteDoctor(doctorId);
    }

    // Lấy ra các user là patient
    @GetMapping("/user/patient")
    @CrossOrigin(origins = "http://localhost:5173")
    public List<PatientDTO> getAllPatient()

    {
        return userService.getAllPatient();
    }


    // Lấy user theo id
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("user/{id}")
    public Users getUserById(@PathVariable int id) {
        return UserService.findUserById(id);
    }


}
