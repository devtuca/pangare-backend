package com.seguradora.paocomovo.loader;

import com.seguradora.paocomovo.model.User;
import com.seguradora.paocomovo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Cria usuário padrão se não existir
        if (!userRepository.existsByUsername("admin")) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("123456"));
            admin.setRole("ADMIN");
            userRepository.save(admin);
            System.out.println("✅ Usuário padrão criado: admin / 123456");
        }

        if (!userRepository.existsByUsername("user")) {
            User user = new User();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("123456"));
            user.setRole("USER");
            userRepository.save(user);
            System.out.println("✅ Usuário padrão criado: user / 123456");
        }
    }
}