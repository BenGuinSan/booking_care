package com.penguinsan.BookingCare.Service;

import com.penguinsan.BookingCare.Model.Clinics;
import com.penguinsan.BookingCare.Repository.ClinicsRepository;
import com.penguinsan.BookingCare.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicService {
    @Autowired
    ClinicsRepository ClinicsRepo;

    // Lấy toàn bộ danh sách phòng khám
    public List<Clinics> getAllClinic()
    {
        return ClinicsRepo.findAll();
    }

    // Lấy một danh sách phòng khám theo id (Sau này sẽ bổ sung thêm là thêm theo tên)

    // Thêm phòng khám mới

    // Chỉnh sửa một phòng khám

    // Xóa một phòng khám

}
