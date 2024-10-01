package com.penguinsan.BookingCare.Service;

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
    public List<Users> getAllDoctor()
    {
        return usersRepo.findAllDoctor();
    }

    // Lấy toàn bộ Patient: User có Role_Id = 3
    public List<Users> getAllPatient()
    {
        return usersRepo.findAllPatient();
    }

    // Lấy User theo Id
    public Users findUserById(int id)
    {
        return usersRepo.findById(id)
                .orElseGet(() -> new Users());
    }

    // Tạo User mới
    public void createUser()
    {

    }

    // Chỉnh sửa User
    public void UpdateUser()
    {

    }

    // Xóa User theo Id
    public void DeleteUser()
    {

    }

    // Thực hiện thêm bác sĩ (Admin)
}
