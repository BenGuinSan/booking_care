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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO){
        if(userService.existsByEmail(registerDTO.getEmail()))
        {
            return new ResponseEntity<>("Email is taken!", HttpStatus.BAD_REQUEST);
        }

        // Tạo đối tượng  người dùng mới
        Users user = new Users();
        user.setFull_name(registerDTO.getFull_name());
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


}
