package com.penguinsan.BookingCare.Controller;

import com.penguinsan.BookingCare.Model.Clinics;
import com.penguinsan.BookingCare.Model.Roles;
import com.penguinsan.BookingCare.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoleController {
    @Autowired
    RoleService RoleService;

    // Lấy toàn bộ danh sách role
    @GetMapping("/role")
    public List<Roles> getAllClinic()
    {
        return RoleService.getAllRole();
    }
}
