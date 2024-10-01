package com.penguinsan.BookingCare.Repository;

import com.penguinsan.BookingCare.Model.Specializations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecializationsRepository extends JpaRepository<Specializations, Integer> {
    // Truy vấn lấy toàn bộ danh sách chuyên khoa
}
