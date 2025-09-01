package com.seguradora.paocomovo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String memberName;
    private int totalVendido;
    private int totalClientes;

    public Member(int id, String memberName, int totalVendido, int totalClientes) {
        this.id = id;
        this.memberName = memberName;
        this.totalVendido = totalVendido;
        this.totalClientes = totalClientes;
    }
}
