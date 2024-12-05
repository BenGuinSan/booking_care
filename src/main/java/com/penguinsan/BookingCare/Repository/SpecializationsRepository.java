package com.penguinsan.BookingCare.Repository;

import com.penguinsan.BookingCare.Model.Specializations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpecializationsRepository extends JpaRepository<Specializations, Integer> {

    // Truy vấn lấy ra toàn bộ danh sách Specializations
    List<Specializations> findAll();

    @Query("SELECT s FROM Specializations s WHERE s.name = :name")
    Optional<Specializations> getSpecializationsByName(@Param("name") String name);
}
