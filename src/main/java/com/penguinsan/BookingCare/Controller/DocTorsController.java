package com.penguinsan.BookingCare.Controller;

import com.penguinsan.BookingCare.Model.Doctors;
import com.penguinsan.BookingCare.Service.DoctorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DocTorsController {

    @Autowired
    DoctorsService doctorService;

    @GetMapping("/doctor")
    public List<Doctors> getAllDoctors()
    {
        return doctorService.getAllDoctors();
    }

}
