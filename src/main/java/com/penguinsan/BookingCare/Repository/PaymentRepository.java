package com.penguinsan.BookingCare.Repository;

import com.penguinsan.BookingCare.Model.Payments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payments, Integer>{

    // Find payment by appointment id



}
