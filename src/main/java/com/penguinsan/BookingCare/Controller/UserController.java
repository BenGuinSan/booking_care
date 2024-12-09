package com.penguinsan.BookingCare.Controller;

import com.penguinsan.BookingCare.DTO.*;
import com.penguinsan.BookingCare.Model.Users;
import com.penguinsan.BookingCare.Service.CloudinaryService;
import com.penguinsan.BookingCare.Service.UserService;
import com.penguinsan.BookingCare.utils.FileUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    @Value("${FOLDER_NAME}")
    private String FOLDER_NAME;

    @Autowired
    UserService userService;

    @Autowired
    CloudinaryService cloudinaryService;

    // Lấy toàn bộ danh sách User
    @GetMapping("/")
    public List<UserDTO> getAllUser()
    {
        return userService.getAllUser();
    }

    // Lấy ra các user là doctor
    @GetMapping("doctor")
    public List<DoctorDTO> getAllDoctor()
    {
        return userService.getAllDoctor();
    }

    // Tải ảnh lên (Doctor)
    @PostMapping(value = "doctor/add/image")
    public ResponseEntity<?> uploadImage(@RequestPart final MultipartFile file){
        CloudinaryResponse response = cloudinaryService.uploadFile(file, FOLDER_NAME);
        return ResponseEntity.ok(response);
    }

    // Tạo mới một doctor (ADMIN)
    @PostMapping(value = "doctor/add")
    public ResponseEntity<String> addDoctor(@RequestBody DoctorRequestDTO doctorRequestDTO) {
        userService.addDoctor(doctorRequestDTO, doctorRequestDTO.getImageUrl());
        return ResponseEntity.ok("Add Doctor successfully!");
    }

    // Chỉnh sửa thông tin doctor (ADMIN)
    @PutMapping("/doctor/update/{doctorId}")
    public void updateDoctor(@PathVariable int doctorId, @RequestBody DoctorRequestDTO doctorRequestDTO){userService.updateDoctor(doctorId,doctorRequestDTO);}

    // Xóa một doctor (ADMIN)
    @DeleteMapping("doctor/delete/{doctorId}")
    public void deleteDoctor(@PathVariable int doctorId){
        userService.deleteDoctor(doctorId);
    }

    // Lấy ra các user là patient
    @GetMapping("patient")
    public List<PatientDTO> getAllPatient()
    {
        return userService.getAllPatient();
    }


    @GetMapping("{id}")
    public Users getUserById(@PathVariable int id) {
        return userService.findUserById(id);
    }

    // get all bác sĩ
    @GetMapping("/doctorAll")
    public List<Users> findAllDoctor() {
        return userService.findAllDoctor();
    }


    //lay bac si trên 5 năm kinh nghiệm
    @GetMapping("/doctor/experience")
    public List<Users> findDoctorExperience() {
        return userService.findDoctorExperience();
    }


}
