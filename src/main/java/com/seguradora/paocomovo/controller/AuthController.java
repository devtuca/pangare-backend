package com.seguradora.paocomovo.controller;

import com.seguradora.paocomovo.model.User;
import com.seguradora.paocomovo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody Map<String, String> request) {
        Map<String, Object> response = new HashMap<>();

        try {
            String username = request.get("username");
            String password = request.get("password");

            // Validações
            if (username == null || username.trim().isEmpty()) {
                response.put("success", false);
                response.put("message", "Username é obrigatório");
                return ResponseEntity.badRequest().body(response);
            }

            if (password == null || password.length() < 3) {
                response.put("success", false);
                response.put("message", "Senha deve ter pelo menos 3 caracteres");
                return ResponseEntity.badRequest().body(response);
            }

            // Verifica se usuário já existe
            if (userRepository.existsByUsername(username)) {
                response.put("success", false);
                response.put("message", "Usuário já existe");
                return ResponseEntity.badRequest().body(response);
            }

            // Cria novo usuário
            User user = new User();
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(password));
            user.setRole("USER");

            userRepository.save(user);

            response.put("success", true);
            response.put("message", "Usuário criado com sucesso");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Erro interno do servidor");
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> request) {
        Map<String, Object> response = new HashMap<>();

        try {
            String username = request.get("username");
            String password = request.get("password");

            // Busca usuário
            User user = userRepository.findByUsername(username).orElse(null);

            if (user == null) {
                response.put("success", false);
                response.put("message", "Usuário não encontrado");
                return ResponseEntity.badRequest().body(response);
            }

            // Verifica senha
            if (!passwordEncoder.matches(password, user.getPassword())) {
                response.put("success", false);
                response.put("message", "Senha incorreta");
                return ResponseEntity.badRequest().body(response);
            }

            // Login bem-sucedido
            response.put("success", true);
            response.put("message", "Login realizado com sucesso");
            response.put("user", Map.of(
                    "id", user.getId(),
                    "username", user.getUsername(),
                    "role", user.getRole()
            ));

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Erro interno do servidor");
            return ResponseEntity.internalServerError().body(response);
        }
    }
}