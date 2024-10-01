package com.penguinsan.BookingCare.Service;

import com.penguinsan.BookingCare.Model.Users;
import com.penguinsan.BookingCare.Repository.UsersRepository;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UsersRepository UsersRepo;

    // Lấy toàn bộ User
    public List<Users> getAllUser()
    {
        return UsersRepo.findAll();
    }

    // Lấy toàn bộ Doctor: User có Role_Id = 2
    public List<Users> getAllDoctor()
    {
        return UsersRepo.findAllDoctor();
    }

    // Lấy toàn bộ Patient: User có Role_Id = 3
    public List<Users> getAllPatient()
    {
        return UsersRepo.findAllPatient();
    }

    // Lấy User theo Id
    public Users findUserById(int id)
    {
        return UsersRepo.findById(id)
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
}
