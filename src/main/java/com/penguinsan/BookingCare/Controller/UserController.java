package com.penguinsan.BookingCare.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @RequestMapping("/")
    public String greet(){
        return "Hello Wrold";
    }

}
