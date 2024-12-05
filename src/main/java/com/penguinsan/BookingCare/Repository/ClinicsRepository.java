package com.penguinsan.BookingCare.Repository;

import com.penguinsan.BookingCare.Model.Clinics;
import com.penguinsan.BookingCare.Model.Specializations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClinicsRepository extends JpaRepository<Clinics, Integer> {
    // Toàn bộ lấy ra toàn bộ danh sách phòng khám

    // Lấy ra clinic theo tên
    @Query("SELECT s FROM Clinics s WHERE s.name = :name")
    Optional<Clinics> getClinicsByName(@Param("name") String name);
}
