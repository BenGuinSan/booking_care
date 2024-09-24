package com.penguinsan.BookingCare.Controller;

import com.penguinsan.BookingCare.Model.Users;
import com.penguinsan.BookingCare.Service.DoctorsService;
import com.penguinsan.BookingCare.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService UserService;

    @RequestMapping("/")
    public String greet(){
        return "Hello World";
    }

    @GetMapping("/users")
    public List<Users> getAllUsers(){
       return UserService.getAllUsers();
    }

}
