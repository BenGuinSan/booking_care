package com.penguinsan.BookingCare.Controller.AuthController;

import com.penguinsan.BookingCare.DTO.AuthDTO.AuthResponseDTO;
import com.penguinsan.BookingCare.DTO.AuthDTO.LoginDTO;
import com.penguinsan.BookingCare.DTO.AuthDTO.RegisterDTO;
import com.penguinsan.BookingCare.Model.Roles;
import com.penguinsan.BookingCare.Model.Users;
import com.penguinsan.BookingCare.Repository.UsersRepository;
import com.penguinsan.BookingCare.Security.JWTGenerator;
import com.penguinsan.BookingCare.Service.RoleService;
import com.penguinsan.BookingCare.Service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import java.util.Map;
import java.util.Optional;

import java.util.Collections;
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
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getEmail(),
                        loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
    }
    @PostMapping("/google-login")
    public ResponseEntity<?> googleLogin(@RequestBody Map<String, String> payload) {
        String token = payload.get("token");

        try {
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                    GoogleNetHttpTransport.newTrustedTransport(),
                    JacksonFactory.getDefaultInstance())
                    .setAudience(Collections.singletonList("828447381869-13uvm7s5nrvrat30ktrifr7ghkgo2tgv.apps.googleusercontent.com"))
                    .build();

            GoogleIdToken idToken = verifier.verify(token);

            if (idToken != null) {
                GoogleIdToken.Payload googlePayload = idToken.getPayload();

                // In ra thông tin payload
                System.out.println("Google Token Verified Successfully");
                System.out.println("Email: " + googlePayload.getEmail());
                System.out.println("Email Verified: " + googlePayload.getEmailVerified());
                System.out.println("Name: " + googlePayload.get("name"));
                System.out.println("User ID: " + googlePayload.getSubject());

                String email = googlePayload.getEmail();
                boolean emailVerified = Boolean.valueOf(googlePayload.getEmailVerified());
                String name = (String) googlePayload.get("name");

                // Kiểm tra xem email đã tồn tại chưa
                Optional<Users> userOptional = userService.findByEmail(email);
                Users user = userOptional.orElse(null);

                if (user == null) {
                    // Nếu chưa, tạo user mới
                    user = new Users();
                    user.setEmail(email);
                    user.setFullName(name);
                    user.setPassword(""); // Không cần mật khẩu
                    user.setAvailable(true);

                    Roles role = roleService.findRoleById(patient); // Gán role mặc định
                    user.setRole(role);
                    userRepo.save(user);
                }

                // Tạo đối tượng Authentication từ email (không có mật khẩu)
                Authentication authentication = new UsernamePasswordAuthenticationToken(email, null);

                // Tạo JWT token bằng cách sử dụng đối tượng Authentication
                String jwtToken = jwtGenerator.generateToken(authentication);

                // Trả về JWT token
                return ResponseEntity.ok(new AuthResponseDTO(jwtToken));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Google token");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Google login failed");
        }
    }

    
    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO){
        if(userService.existsByEmail(registerDTO.getEmail()))
        {
            return new ResponseEntity<>("Email is taken!", HttpStatus.BAD_REQUEST);
        }

        // Tạo đối tượng  người dùng mới
        Users user = new Users();
        user.setFullName(registerDTO.getFull_name());
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
    public ResponseEntity<String> logout(){
        SecurityContextHolder.getContext().setAuthentication(null);
        return new ResponseEntity<>("Successfully logged out", HttpStatus.OK);
    }

    @GetMapping("detail")
    public Object userDetail(Authentication authentication){
        String email = authentication.getName();
        return userService.getUserDetails(email);
    }

    // Lấy thông tin người dùng đã đăng nhập
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