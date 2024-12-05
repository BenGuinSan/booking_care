package com.penguinsan.BookingCare.Service;

import com.penguinsan.BookingCare.DTO.SpecializationRequestDTO;
import com.penguinsan.BookingCare.Model.Clinics;
import com.penguinsan.BookingCare.Model.Specializations;
import com.penguinsan.BookingCare.Repository.ClinicsRepository;
import com.penguinsan.BookingCare.Repository.SpecializationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpecializationService {
    @Autowired
    SpecializationsRepository sepecialiazationRepo;

    // Lấy toàn bộ danh sách chuyên khoa
    public List<Specializations> getAllSpecialization()
    {
        return sepecialiazationRepo.findAll();
    }

    // Lấy một chuyên khóa
    public Optional<Specializations> getSpecializationsByName(String name) {return sepecialiazationRepo.getSpecializationsByName(name);}

    // Thêm một chuyên khoa (ADMIN)
    public void addSpecialization(SpecializationRequestDTO splRequestDTO){
        Specializations specialization = new Specializations();

        specialization.setName(splRequestDTO.getName());

        sepecialiazationRepo.save(specialization);
    }

    // Xóa một chuyên khoa
    public void deleteSpecializationByName(String name){
        Optional<Specializations> specialization = getSpecializationsByName(name);

        if (specialization.isPresent()) {
            sepecialiazationRepo.delete(specialization.get());
            System.out.println("Specialization with name '" + name + "' deleted successfully.");
        } else {
            System.out.println("No specialization found with name '" + name + "'");
        }

    }
}
