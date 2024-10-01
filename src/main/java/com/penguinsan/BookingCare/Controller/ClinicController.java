package com.penguinsan.BookingCare.Controller;

import com.penguinsan.BookingCare.Model.Clinics;
import com.penguinsan.BookingCare.Service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClinicController {
    @Autowired
    ClinicService ClinicService;

    // Lấy toàn bộ danh sách phòng khám
    @GetMapping("/clinic")
    public List<Clinics> getAllClinic()
    {
        return ClinicService.getAllClinic();
    }
}
