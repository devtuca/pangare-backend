package com.seguradora.paocomovo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserController {

    public ResponseEntity<String> getUser() {
        return ResponseEntity.ok("sucesso!");
    }
}
