package com.penguinsan.BookingCare.Repository;

import com.penguinsan.BookingCare.Model.Doctors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorsRepository extends JpaRepository<Doctors, Integer> {
}
