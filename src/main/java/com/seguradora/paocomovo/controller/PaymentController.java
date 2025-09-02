package com.seguradora.paocomovo.controller;

import com.asaas.apisdk.models.PaymentGetResponseDto;
import com.asaas.apisdk.models.PaymentListResponseDto;
import com.asaas.apisdk.services.PaymentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.seguradora.paocomovo.model.Customer;
import com.seguradora.paocomovo.model.Invoice;
import com.seguradora.paocomovo.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private PaymentService paymentService;


    @PostMapping("/criar")
    public void createCustomer(@RequestBody Customer customer) {

        System.out.println("Iniciando criação de cliente");
        System.out.println("CostumerName: " + customer.getName());
        System.out.println("CostumerCarSign: " + customer.getCarSign());
        System.out.println("CPF: " + customer.getCpf());

        invoiceService.createCustomer(customer.getCarSign(), customer.getName(), customer.getCpf());
    }

    @PostMapping("/deletar")
    public void deleteCustomer(@RequestBody Customer customer) {
        //invoiceService.deleteCustomer(customer);
    }

    @GetMapping
    public List<Invoice> getAllPaymentsPendent() throws JsonProcessingException {
        return invoiceService.getAllPaymentsPendents();
    }

}