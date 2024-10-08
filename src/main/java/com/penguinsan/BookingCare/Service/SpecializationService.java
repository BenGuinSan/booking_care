package com.penguinsan.BookingCare.Service;

import com.penguinsan.BookingCare.Model.Clinics;
import com.penguinsan.BookingCare.Model.Specializations;
import com.penguinsan.BookingCare.Repository.ClinicsRepository;
import com.penguinsan.BookingCare.Repository.SpecializationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecializationService {
    @Autowired
    SpecializationsRepository SepecialiazationRepo;

    // Lấy toàn bộ danh sách chuyên khoa
    public List<Specializations> getAllSpecialization()
    {
        return SepecialiazationRepo.findAll();
    }

    // Lấy một chuyên khóa

    // Thêm một chuyên khoa

    // Chỉnh sửa một chuyên khoa

    // Xóa một chuyên khoa
}
