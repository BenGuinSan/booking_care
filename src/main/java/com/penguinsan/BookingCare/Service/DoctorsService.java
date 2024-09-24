package com.penguinsan.BookingCare.Service;

import com.penguinsan.BookingCare.Model.Doctors;
import com.penguinsan.BookingCare.Repository.DoctorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorsService {
    @Autowired
    DoctorsRepository DoctorsRepo;

    public List<Doctors> getAllDoctors() {
        return DoctorsRepo.findAll();
    }

}
