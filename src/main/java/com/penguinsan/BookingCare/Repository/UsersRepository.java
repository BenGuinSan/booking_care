package com.penguinsan.BookingCare.Repository;

import com.penguinsan.BookingCare.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    @Query(
            value = "SELECT * FROM USERS u WHERE u.Role_Id = 2",
            nativeQuery = true)
    List<Users> findAllDoctor();

    @Query(
            value = "SELECT * FROM USERS u WHERE u.Role_Id = 3",
            nativeQuery = true)
    List<Users> findAllPatient();
}
