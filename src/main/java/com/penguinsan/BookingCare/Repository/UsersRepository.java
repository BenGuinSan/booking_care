package com.penguinsan.BookingCare.Repository;

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
            value = "SELECT u FROM Users u WHERE u.role.id = 2")
    List<Users> findAllDoctor();

    @Query(
            value = "SELECT u FROM Users u WHERE u.role.id = 3")
    List<Users> findAllPatient();

   //lấy thông tin của bác sĩ trên 5 năm kinh nghiệm
    @Query(
            value = "SELECT u FROM Users u WHERE u.Experience >= 5")
    List<Users> findDoctorExperience();

    @Modifying
    @Transactional
    @Query("UPDATE Users u SET u.fullName = :fullName, u.Booking_Fee = :bookingFee, u.specialization = :specialization WHERE u.user_Id = :userId AND u.role.role_Id = 2")
    int updateDoctor(
            @Param("userId") int userId,
            @Param("fullName") String fullName,
            @Param("bookingFee") float bookingFee,
            @Param("specialization") Specializations specialization
    );

    @Modifying
    @Transactional
    @Query("UPDATE Users u SET u.fullName = :fullName, u.Phone = :phone, u.address = :address, u.Gender = :gender, u.DateOfBirth = :dateOfBirth WHERE u.email = :email AND u.role.role_Id = 3")
    int updatePatient(
            @Param("email") String email,
            @Param("fullName") String fullName,
            @Param("phone") String phone,
            @Param("gender") boolean gender,
            @Param("address") String address,
            @Param("dateOfBirth") Date DateOfBirth
    );
}
