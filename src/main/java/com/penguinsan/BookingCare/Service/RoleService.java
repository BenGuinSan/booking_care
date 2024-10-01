package com.penguinsan.BookingCare.Service;

import com.penguinsan.BookingCare.Model.Roles;
import com.penguinsan.BookingCare.Model.Users;
import com.penguinsan.BookingCare.Repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    RolesRepository RoleRepo;

    // Lấy toàn bộ danh sách Role
    public List<Roles> getAllRole()
    {
        return RoleRepo.findAll();
    }

    // Lấy một Role theo id
    public Roles findRoleById(int id)
    {
        return RoleRepo.findById(id)
                .orElseGet(() -> new Roles());
    }

    // Thêm một Role mới

    // Chỉnh sửa một role

    // Xóa một role
}
