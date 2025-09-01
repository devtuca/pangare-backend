package com.seguradora.paocomovo.model;

import com.asaas.apisdk.models.CustomerGetResponseDto;
import com.asaas.apisdk.models.InvoiceGetResponseDto;
import com.asaas.apisdk.models.PaymentGetResponseDto;
import com.asaas.apisdk.services.CustomerService;
import com.asaas.apisdk.services.PaymentService;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@NoArgsConstructor
public class Invoice {

    @Id
    private String id;
    private String ownerName;
    private String carSign;
    private String ownerCPF;
    private BigDecimal orderPrice;
    private String expiryDate;
    private String invoiceType;
    private String status;


    public Invoice(PaymentGetResponseDto invoice, CustomerService customerService) {

        CustomerGetResponseDto customer = getCustomerIdByPayment(invoice, customerService);

        this.id = customer.getId();
        this.ownerName = customer.getName();
        this.carSign = customer.getExternalReference();
        this.ownerCPF = customer.getCpfCnpj();
        this.orderPrice = BigDecimal.valueOf(invoice.getValue());
        this.expiryDate = invoice.getDueDate();
        this.invoiceType = invoice.getBillingType().name();
        this.status = invoice.getStatus().name();

        System.out.println("Customer: " + customer);
        System.out.println("Invoice: " + invoice);
        System.out.println("Status: " + invoice.getStatus().name());

    }

    public CustomerGetResponseDto getCustomerIdByPayment(PaymentGetResponseDto payment, CustomerService customerService) {
        return customerService.retrieveASingleCustomer(payment.getCustomer());
    }

}
