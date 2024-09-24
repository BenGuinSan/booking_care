package com.penguinsan.BookingCare.Repository;

import com.penguinsan.BookingCare.Model.Patients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientsRepository extends JpaRepository<Patients, Integer> {
}
