package com.penguinsan.BookingCare.Controller.AuthController;

import com.penguinsan.BookingCare.DTO.AuthDTO.AuthResponseDTO;
import com.penguinsan.BookingCare.DTO.AuthDTO.LoginDTO;
import com.penguinsan.BookingCare.DTO.AuthDTO.RegisterDTO;
import com.penguinsan.BookingCare.Model.Roles;
import com.penguinsan.BookingCare.Model.Users;
import com.penguinsan.BookingCare.Repository.UsersRepository;
import com.penguinsan.BookingCare.Security.CustomUserDetailsService;
import com.penguinsan.BookingCare.Security.JWTGenerator;
import com.penguinsan.BookingCare.Service.RoleService;
import com.penguinsan.BookingCare.Service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Value("${patient.id}")
    private int patient;

    @Autowired
    private UsersRepository userRepo;

    @Autowired
    private JWTGenerator jwtGenerator;

    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(UserService userService, AuthenticationManager authenticationManager, RoleService roleService, PasswordEncoder passwordEncoder, JWTGenerator jwtGenerator) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDTO, HttpServletResponse response){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getEmail(),
                        loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        String token = jwtGenerator.generateToken(authentication);
        Cookie cookie = new Cookie("accessToken", token);
        cookie.setMaxAge(7 * 24 * 60 * 60000);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        response.addCookie(cookie);
        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO){
        if(userService.existsByEmail(registerDTO.getEmail()))
        {
            return new ResponseEntity<>("Email is taken!", HttpStatus.BAD_REQUEST);
        }
        // Tạo đối tượng  người dùng mới
        Users user = new Users();
        user.setFullName(registerDTO.getFullName());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setAvailable(true);
        // Set role (patient) cho đối tượng người dùng vừa tạo
        Roles role = roleService.findRoleById(patient);
        user.setRole(role);
        userRepo.save(user);
        return new ResponseEntity<>("User registered success", HttpStatus.OK);
    }

    @PostMapping("logout")
    public ResponseEntity<String> logout(HttpServletRequest request){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request,
            null, auth);

        }
        return ResponseEntity.ok("Logout successful!");
    }

    // Lay thong tin chi tiet user qua email
    @GetMapping("/{email}")
    public Object userDetail(Authentication authentication, @PathVariable String email){
        return userService.getUserDetails(email);
    }

    // Lấy thông tin người dùng đã đăng nhập (ID)
    @GetMapping("/profile")
    public int getUserProfile() {
        // Lấy thông tin xác thực từ SecurityContextHolder
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            // Lấy username từ token
            String username = authentication.getName();
            int userId = userService.getUserIdByEmail(username);
            return userId;
        } else {
            return 0;
        }
    }

//    @PostMapping("/verify")
//    public ResponseEntity<?> verifyUser(@RequestBody VerifyUserDto verifyUserDto) {
//        try {
//            authenticationService.verifyUser(verifyUserDto);
//            return ResponseEntity.ok("Account verified successfully");
//        } catch (RuntimeException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }
//
//    @PostMapping("/resend")
//    public ResponseEntity<?> resendVerificationCode(@RequestParam String email) {
//        try {
//            authenticationService.resendVerificationCode(email);
//            return ResponseEntity.ok("Verification code sent");
//        } catch (RuntimeException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }
}
