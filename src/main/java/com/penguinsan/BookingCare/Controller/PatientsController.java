package com.penguinsan.BookingCare.Controller;

import com.penguinsan.BookingCare.Model.Doctors;
import com.penguinsan.BookingCare.Model.Patients;
import com.penguinsan.BookingCare.Service.PatientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PatientsController {
    @Autowired
    PatientsService patientService;

    @GetMapping("/patients")
    public List<Patients> getAllDoctors()
    {
        return patientService.getAllPatients();
    }
}
