package com.penguinsan.BookingCare.Service;

import com.penguinsan.BookingCare.DTO.DoctorDTO;
import com.penguinsan.BookingCare.DTO.PatientDTO;
import com.penguinsan.BookingCare.DTO.UserDTO;
import com.penguinsan.BookingCare.Mapper.UserMapper;
import com.penguinsan.BookingCare.Model.Users;
import com.penguinsan.BookingCare.Repository.UsersRepository;
import org.hibernate.annotations.NotFound;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    UsersRepository usersRepo;

    @Autowired
    private UserMapper userMapper;

    // Lấy toàn bộ User(DTO)
    public List<UserDTO> getAllUser()
    {
        var users = usersRepo.findAll();

        List<UserDTO> userDTOs = users.stream()
                .map(userMapper::toUserDTO)
                .collect(Collectors.toList());

        return userDTOs;
    }

    // Lấy toàn bộ Doctor: User có Role_Id = 2
    public List<DoctorDTO> getAllDoctor()
    {
        var doctor = usersRepo.findAllDoctor();
        List<DoctorDTO> doctorDTOs = doctor.stream()
                .map(userMapper::toDoctorDTO)
                .collect(Collectors.toList());

        return doctorDTOs;
    }

    // Lấy toàn bộ Patient: User có Role_Id = 3
    public List<PatientDTO> getAllPatient()
    {
        var patient = usersRepo.findAllPatient();
        List<PatientDTO> patientDTOs = patient.stream()
                .map(userMapper::toPatientDTO)
                .collect(Collectors.toList());
        return patientDTOs;
    }

    // Tìm kiếm người dùng theo email
    public Optional<Users> findByEmail(String email)
    {
        return usersRepo.findByEmail(email);
    }

//    // Kiểm tra người dùng đã tồn tại hay chưa
    public Boolean existsByEmail(String email)
    {
        return usersRepo.existsByEmail(email);
    }




}
