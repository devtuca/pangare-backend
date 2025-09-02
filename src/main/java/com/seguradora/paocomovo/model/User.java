package com.seguradora.paocomovo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;

    // Construtores
    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

