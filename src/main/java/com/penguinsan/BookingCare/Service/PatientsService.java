package com.penguinsan.BookingCare.Service;

import com.penguinsan.BookingCare.Model.Doctors;
import com.penguinsan.BookingCare.Model.Patients;
import com.penguinsan.BookingCare.Repository.PatientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientsService {
    @Autowired
    PatientsRepository PatientsRepo;

    public List<Patients> getAllPatients() {
        return PatientsRepo.findAll();
    }


}
