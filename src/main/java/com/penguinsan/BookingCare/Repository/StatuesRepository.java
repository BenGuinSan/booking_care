package com.penguinsan.BookingCare.Repository;

import com.penguinsan.BookingCare.Model.Statues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatuesRepository extends JpaRepository<Statues, Integer> {
}
