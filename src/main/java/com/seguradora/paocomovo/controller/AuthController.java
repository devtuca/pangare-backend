package com.seguradora.paocomovo.controller;


import com.seguradora.paocomovo.dto.LoginRequestDTO;
import com.seguradora.paocomovo.dto.ResponseDTO;
import com.seguradora.paocomovo.model.User;
import com.seguradora.paocomovo.repository.UserRepository;
import com.seguradora.paocomovo.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")

public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;


//    public AuthController(AuthenticationManager authenticationManager,
//                          UserDetailsService userDetailsService,
//                          TokenService tokenService) {
//        this.authenticationManager = authenticationManager;
//        this.userDetailsService = userDetailsService;
//        this.tokenService = tokenService;
//
//    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody LoginRequestDTO body) {
        User user = userRepository.findByUsername(body.username()).orElseThrow(() -> new RuntimeException("Username not found"));
        if (passwordEncoder.matches(body.password(), user.getPassword())) {
            String token = this.tokenService.generateToken(user);
            System.out.println("o token tentado logar é: " + token);
            return ResponseEntity.ok(new ResponseDTO(user.getUsername(), token));
        } else {
            System.out.println("token n tem");
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> register(@RequestBody LoginRequestDTO body) {


        //User user = userRepository.findByUsername(body.username()).orElseThrow(() -> new RuntimeException("Username not found"));
        if (userRepository.findByUsername(body.username()).isPresent()) {
            System.out.println("ja existe");
            return ResponseEntity.badRequest().build();
        }

        String passwordEncoded = passwordEncoder.encode(body.password());
        User user = new User(body.username(), passwordEncoded);

        user.setPassword(passwordEncoded);
        userRepository.save(user);
        String token = tokenService.generateToken(user);
        System.out.println("Token: " + token);
        return ResponseEntity.ok(new ResponseDTO(user.getUsername(), token));
    }
}
