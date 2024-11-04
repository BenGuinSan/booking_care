package com.penguinsan.BookingCare.Repository;

import com.penguinsan.BookingCare.Model.Clinics;
import com.penguinsan.BookingCare.Model.Roles;
import com.penguinsan.BookingCare.Model.Specializations;
import com.penguinsan.BookingCare.Model.Users;
import jakarta.annotation.Nullable;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import java.time.Year;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

    Optional<Users> findByEmail(String email);

    Boolean existsByEmail(String email);

    @Query(
            value = "SELECT * FROM USERS u WHERE u.role_Id = 2",
            nativeQuery = true)
    List<Users> findAllDoctor();

    @Query(
            value = "SELECT * FROM USERS u WHERE u.role_Id = 3",
            nativeQuery = true)
    List<Users> findAllPatient();

    //lấy doctor by id
    @Query("SELECT u FROM Users u WHERE u.user_Id = :userId AND u.role.role_Id = 2")
    Optional<Users> getDoctorById(@Param("userId") int userId);

    // Chỉnh sửa thông tin doctor theo ID


    @Modifying
    @Transactional
    @Query("UPDATE Users u SET u.full_name = :fullName, u.password = :password, u.email = :email, u.phone = :phone, " +
            "u.degree = :degree, u.bookingFee = :bookingFee, u.image = :image, u.gender = :gender, " +
            "u.address = :address, u.DateOfBirth = :dateOfBirth, u.experience = :experience, " +
            "u.specialization = :specialization WHERE u.user_Id = :userId AND u.role.role_Id = 2")
    int updateDoctor(
            @Param("userId") int userId,
            @Param("fullName") String fullName,
            @Param("password") String password,
            @Param("email") String email,
            @Param("phone") String phone,
            @Param("degree") String degree,
            @Param("bookingFee") float bookingFee,
            @Param("image") String image,
            @Param("gender") boolean gender,
            @Param("address") String address,
            @Param("dateOfBirth") Date DateOfBirth,
            @Param("experience") Year experience,
            @Param("specialization") Specializations specialization
    );



}
