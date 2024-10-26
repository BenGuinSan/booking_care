package com.penguinsan.BookingCare.Repository;

import com.penguinsan.BookingCare.Model.Users;
import jakarta.annotation.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {


    Optional<Users> findByEmail(String email);

    Boolean existsByEmail(String email);

    @Query(
            value = "SELECT * FROM USERS u WHERE u.Role_Id = 2",
            nativeQuery = true)
    List<Users> findAllDoctor();

    @Query(
            value = "SELECT * FROM USERS u WHERE u.Role_Id = 3",
            nativeQuery = true)
    List<Users> findAllPatient();




}
