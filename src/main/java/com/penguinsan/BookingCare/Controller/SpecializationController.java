package com.penguinsan.BookingCare.Controller;

import com.penguinsan.BookingCare.Model.Specializations;
import com.penguinsan.BookingCare.Service.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SpecializationController {
    @Autowired
    SpecializationService SpecializationService;

    // Lấy toàn bộ danh sách chuyên khoa
    @GetMapping("/specialization")
    public List<Specializations> getAllSpecialization()
    {
       return SpecializationService.getAllSpecialization();
    }
}
