package com.penguinsan.BookingCare.Repository;

import com.penguinsan.BookingCare.Model.Payments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentsRepository extends JpaRepository<Payments, Integer> {
}
