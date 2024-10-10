package com.penguinsan.BookingCare.Security;

import com.penguinsan.BookingCare.Model.Roles;
import com.penguinsan.BookingCare.Model.Users;
import com.penguinsan.BookingCare.Repository.UsersRepository;
import com.penguinsan.BookingCare.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepo;

    @Autowired
    public CustomUserDetailsService(UsersRepository usersRepo)
    {
        this.usersRepo = usersRepo;
    }

    // Spring Security tìm kiếm thông tin người dùng (cụ thể ở đây thông qua email) và thực hiện quá trình xác thực.
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = usersRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Email not found"));
        // thông tin của người dùng (email, mật khẩu, vai trò) sẽ được lấy ra và đóng gói vào một đối tượng UserDetails
        return new User(
                user.getEmail(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRole_Id())
        );
    }

    // Chuyển đổi vai trò của người dùng (được lưu trữ dưới dạng Role) thành một danh sách các quyền hạn (GrantedAuthority) mà Spring Security có thể hiểu
    private Collection<GrantedAuthority> mapRolesToAuthorities(Roles role) {
        return Collections.singletonList(new SimpleGrantedAuthority(role.getName()));
    }


}
