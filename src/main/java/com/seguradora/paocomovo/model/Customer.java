package com.seguradora.paocomovo.model;

import com.asaas.apisdk.models.CustomerGetResponseDto;
import com.asaas.apisdk.services.CustomerService;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Customer {


    @Id
    public String id;
    private String cpf;
    private String name;
    private String carSign;
    private String email;

    public Customer(CustomerGetResponseDto customerGetResponseDto) {
        this.id = customerGetResponseDto.getId();
        this.cpf =  customerGetResponseDto.getCpfCnpj();
        this.name =  customerGetResponseDto.getName();
        this.carSign = customerGetResponseDto.getExternalReference();
        this.email = customerGetResponseDto.getEmail();
    }

}
