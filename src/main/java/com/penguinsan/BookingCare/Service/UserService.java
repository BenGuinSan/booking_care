package com.penguinsan.BookingCare.Service;

import com.penguinsan.BookingCare.DTO.DoctorDTO;
import com.penguinsan.BookingCare.DTO.PatientDTO;
import com.penguinsan.BookingCare.DTO.UserDTO;
import com.penguinsan.BookingCare.Mapper.UserMapper;
import com.penguinsan.BookingCare.Model.Clinics;
import com.penguinsan.BookingCare.Model.Roles;
import com.penguinsan.BookingCare.Model.Specializations;
import com.penguinsan.BookingCare.Model.Users;
import com.penguinsan.BookingCare.Repository.ClinicsRepository;
import com.penguinsan.BookingCare.Repository.RolesRepository;
import com.penguinsan.BookingCare.Repository.SpecializationsRepository;
import com.penguinsan.BookingCare.Repository.UsersRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Value("${doctor.id}")
    private int doctor;

    private PasswordEncoder passwordEncoder;

    @Autowired
    RolesRepository roleRepo;
    @Autowired
    SpecializationsRepository specializationRepo;

    @Autowired
    static UsersRepository usersRepo;

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

    // Lấy thông tin của người dùng đang đăng nhập
    public PatientDTO getUserDetails(String email) {
        Optional<Users> userDetail = usersRepo.findByEmail(email);
        if (userDetail.isPresent()) {
            Users user = userDetail.get();
            return userMapper.toPatientDTO(user);
        } else {
            throw new UsernameNotFoundException("User not found");
        }
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

    // Tìm kiếm người dùng theo id
    public static Users findUserById(int id){
        return usersRepo.findById(id).orElse(new Users());
    }

    // Kiểm tra người dùng đã tồn tại hay chưa
    public Boolean existsByEmail(String email)
    {
        return usersRepo.existsByEmail(email);
    }

    // Tạo mới một bác sĩ (ADMIN)
    public void addDoctor(DoctorDTO doctorDTO) {
        // Role: Doctor
        Roles role = roleRepo.findById(doctor).orElse(null);

        Users user = new Users();
        user.setFull_name(doctorDTO.getFullName());
        user.setEmail(doctorDTO.getEmail());
        user.setPassword(passwordEncoder.encode(doctorDTO.getPassword()));
        user.setDegree(doctorDTO.getDegree());
        user.setDateOfBirth(doctorDTO.getDateOfBirth());
        user.setAvailable(true);
        user.setPhone(doctorDTO.getPhone());
        user.setRole(role);

        user.setSpecialization(doctorDTO.getSpecialization_Id());
        user.setClinic(doctorDTO.getClinic_Id());

        usersRepo.save(user);
    }

    // Xóa một người dùng (bác sĩ/bệnh nhân)
    public void deleteDoctor(int doctorId){
        Optional<Users> optionalDoctorToDelete = usersRepo.findById(doctorId);
        if (optionalDoctorToDelete.isPresent()) {
            Users doctorToDelete = optionalDoctorToDelete.get();
            usersRepo.delete(doctorToDelete);
        } else {
            throw new UsernameNotFoundException("Doctor with ID " + doctorId + " not found");
        }
    }
    // lấy doctor by id
    public Optional<Users> findDoctorById(int id){

        return usersRepo.getDoctorById(id);
    }
    // Chỉnh sửa thông tin người dùng (bác sĩ/bệnh nhân)


    @Transactional
    public Optional<Specializations> getSpecializationByName(String name) {
        return specializationRepo.getSpecializationsByName(name);
    }



    public boolean updateDoctor(int userId,DoctorDTO doctorDTO) {
        // Tìm kiếm specialization
        Optional<Specializations> specializationOpt = getSpecializationByName(doctorDTO.getSpecialization_Id().getName());
        // Kiểm tra nếu tìm thấy specialization
        if (!specializationOpt.isPresent()) {
            return false; // Không tìm thấy
        }

        // Cập nhật thông tin bác sĩ
        int rowsUpdated = usersRepo.updateDoctor(userId, doctorDTO.getFullName(), doctorDTO.getPassword(), doctorDTO.getEmail(), doctorDTO.getPhone(),
                doctorDTO.getDegree(),doctorDTO.getBooking_Fee() , doctorDTO.getImage(), doctorDTO.isGender(), doctorDTO.getAddress(), doctorDTO.getDateOfBirth(), doctorDTO.getExperience(),
                specializationOpt.get());

        return rowsUpdated > 0; // Trả về true nếu có ít nhất 1 dòng được cập nhật
    }

}
