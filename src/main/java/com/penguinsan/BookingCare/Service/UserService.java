package com.penguinsan.BookingCare.Service;

import com.penguinsan.BookingCare.Model.Users;
import com.penguinsan.BookingCare.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UsersRepository UsersRepo;

    public List<Users> getAllUsers()
    {
        return UsersRepo.findAll();
    }
}
