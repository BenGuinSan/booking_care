package com.penguinsan.BookingCare.Repository;

import com.penguinsan.BookingCare.Model.Clinics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicsRepository extends JpaRepository<Clinics, Integer> {
}
