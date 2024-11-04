package com.penguinsan.BookingCare.Controller;

import com.penguinsan.BookingCare.Model.Specializations;
import com.penguinsan.BookingCare.Service.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class SpecializationController {
    @Autowired
    SpecializationService SpecializationService;

    // Lấy toàn bộ danh sách chuyên khoa
    @GetMapping("/specialization")
    @CrossOrigin(origins = "http://localhost:5173")

    public List<Specializations> getAllSpecialization()
    {
       return SpecializationService.getAllSpecialization();
    }
    @GetMapping("/specialization/{name}")
    @CrossOrigin(origins = "http://localhost:5173")

    public Optional<Specializations> getSpecializationsByName(@PathVariable  String name) {return SpecializationService.getSpecializationsByName(name);}
}
