package com.penguinsan.BookingCare.Service;

import com.penguinsan.BookingCare.DTO.DoctorDTO;
import com.penguinsan.BookingCare.DTO.DoctorRequestDTO;
import com.penguinsan.BookingCare.DTO.PatientDTO;
import com.penguinsan.BookingCare.DTO.UserDTO;
import com.penguinsan.BookingCare.Mapper.UserMapper;
import com.penguinsan.BookingCare.Model.Clinics;
import com.penguinsan.BookingCare.Model.Roles;
import com.penguinsan.BookingCare.Model.Specializations;
import com.penguinsan.BookingCare.Model.Users;
import com.penguinsan.BookingCare.Repository.*;
import com.penguinsan.BookingCare.Security.JWTGenerator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Value("${doctor.id}")
    private int doctor;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    RolesRepository roleRepo;
    @Autowired
    SpecializationsRepository specializationRepo;
    @Autowired
    ClinicsRepository clinicRepo;

    @Autowired
    UsersRepository usersRepo;

    @Autowired
    private UserMapper userMapper;

    // Lấy toàn bộ User(DTO)
    public List<UserDTO> getAllUser() {
        var users = usersRepo.findAll();

        List<UserDTO> userDTOs = users.stream()
                .map(userMapper::toUserDTO)
                .collect(Collectors.toList());

        return userDTOs;
    }

    // Lấy thông tin của người dùng đang đăng nhập
    public UserDTO getUserDetails(String email) {
        Optional<Users> userDetail = usersRepo.findByEmail(email);
        if (userDetail.isPresent()) {
            Users user = userDetail.get();
            return userMapper.toUserDTO(user);
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    // Lấy toàn bộ Doctor: User có Role_Id = 2
    public List<DoctorDTO> getAllDoctor() {
        var doctor = usersRepo.findAllDoctor();
        List<DoctorDTO> doctorDTOs = doctor.stream()
                .map(userMapper::toDoctorDTO)
                .collect(Collectors.toList());
        return doctorDTOs;
    }

    // Lấy toàn bộ Patient: User có Role_Id = 3
    public List<PatientDTO> getAllPatient() {
        var patient = usersRepo.findAllPatient();
        List<PatientDTO> patientDTOs = patient.stream()
                .map(userMapper::toPatientDTO)
                .collect(Collectors.toList());
        return patientDTOs;
    }

    // Tìm kiếm người dùng theo email
    public Optional<Users> findByEmail(String email) {
        return usersRepo.findByEmail(email);
    }

    // Tìm kiếm người dùng theo id
    public Users findUserById(Integer id) {
        Users user = usersRepo.findById(id).orElse(null);
        return user;
    }

    // Kiểm tra người dùng đã tồn tại hay chưa
    public Boolean existsByEmail(String email) {
        return usersRepo.existsByEmail(email);
    }

    // Tạo mới một bác sĩ (ADMIN)
    public void addDoctor(DoctorRequestDTO doctorRequestDTO, String imageUrl) {
        // Role: Doctor
        Roles role = roleRepo.findById(doctor).orElse(null);

        // Specialization
        Specializations specialization  = specializationRepo.getSpecializationsByName(doctorRequestDTO.getSpecialization()).orElse(null);

        // Clinic
//        Clinics clinic = clinicRepo.getClinicsByName(doctorRequestDTO.getClinic()).orElse(null);

        Users user = new Users();
        user.setFullName(doctorRequestDTO.getFullName());
        user.setEmail(doctorRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(doctorRequestDTO.getPassword()));
//        user.setPhone(doctorRequestDTO.getPhone());
        user.setDegree(doctorRequestDTO.getDegree());
        user.setBooking_Fee(doctorRequestDTO.getBooking_Fee());
        user.setAddress(doctorRequestDTO.getAddress());
        user.setExperience(doctorRequestDTO.getExperience());
        user.setSpecialization(specialization);
//        user.setDateOfBirth(doctorRequestDTO.getDateOfBirth());
//        user.setClinic_Id(clinic);
        user.setDescription(doctorRequestDTO.getDescription());
        user.setImage(imageUrl);
        user.setRole(role);
        user.setGender(true);
        user.setAvailable(true);

        usersRepo.save(user);
    }

    // Xóa một người dùng (bác sĩ/bệnh nhân)
    public void deleteDoctor(int doctorId) {
        Optional<Users> optionalDoctorToDelete = usersRepo.findById(doctorId);
        if (optionalDoctorToDelete.isPresent()) {
            Users doctorToDelete = optionalDoctorToDelete.get();
            usersRepo.delete(doctorToDelete);
        } else {
            throw new UsernameNotFoundException("Doctor with ID " + doctorId + " not found");
        }
    }

    // Lấy toàn bộ bác sĩ
    public List<Users> findAllDoctor() {
        return usersRepo.findAllDoctor();
    }

    // Lấy danh sach bác sĩ có kinh nghiệm trên 5 năm
    public List<Users> findDoctorExperience() {
        return usersRepo.findDoctorExperience();
    }

    // Lay thong tin user.id theo email
    public int getUserIdByEmail(String email) {
        Optional<Users> user = usersRepo.findByEmail(email);
        return user.map(Users::getUser_Id).orElse(0);
    }

//    @Transactional
//    public Optional<Specializations> getSpecializationByName(String name) {
//        return specializationRepo.getSpecializationsByName(name);
//    }

    public boolean updateDoctor(int userId, DoctorRequestDTO doctorRequestDTO) {
        // Tìm kiếm specialization
        Specializations specialization  = specializationRepo.getSpecializationsByName(doctorRequestDTO.getSpecialization()).orElse(null);
        // Kiểm tra nếu tìm thấy specialization
        if (specialization == null) {
            return false; // Specialization not found
        }

        // Cập nhật thông tin bác sĩ
        int rowsUpdated = usersRepo.updateDoctor(userId, doctorRequestDTO.getFullName(), doctorRequestDTO.getPassword(), doctorRequestDTO.getEmail(), doctorRequestDTO.getPhone(),
                doctorRequestDTO.getDegree(), doctorRequestDTO.getBooking_Fee() , doctorRequestDTO.getImageUrl(),doctorRequestDTO.isGender(), doctorRequestDTO.getAddress(), doctorRequestDTO.getDateOfBirth(), doctorRequestDTO.getExperience(),
                specialization);

        return rowsUpdated > 0; // Trả về true nếu có ít nhất 1 dòng được cập nhật
    }

}
