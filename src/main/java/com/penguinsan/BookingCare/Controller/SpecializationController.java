package com.penguinsan.BookingCare.Controller;

import com.penguinsan.BookingCare.DTO.DoctorRequestDTO;
import com.penguinsan.BookingCare.DTO.SpecializationRequestDTO;
import com.penguinsan.BookingCare.Model.Specializations;
import com.penguinsan.BookingCare.Service.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SpecializationController {
    @Autowired
    SpecializationService specializationService;

    // Lấy toàn bộ danh sách chuyên khoa
    @GetMapping("/specialization")
    public List<Specializations> getAllSpecialization()
    {
       return specializationService.getAllSpecialization();
    }

    @PostMapping("/specialization")
    public ResponseEntity<String> addSpecialization(@RequestBody SpecializationRequestDTO specializationRequestDTO){
        specializationService.addSpecialization(specializationRequestDTO);
        return ResponseEntity.ok("Add Specialization successfully!");
    }

    @DeleteMapping("/specialization/{name}")
    public void deleteSpecialization(@PathVariable String name){
       specializationService.deleteSpecializationByName(name);
    }
}
