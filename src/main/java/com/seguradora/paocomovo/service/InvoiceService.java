package com.seguradora.paocomovo.service;

import com.asaas.apisdk.models.*;
import com.asaas.apisdk.services.CustomerService;
import com.asaas.apisdk.services.PaymentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.seguradora.paocomovo.model.Customer;
import com.seguradora.paocomovo.model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceService {

    private final CustomerService customerService;
    private final PaymentService paymentService;

    @Autowired
    public InvoiceService(CustomerService customerService, PaymentService paymentService) {
        this.customerService = customerService;
        this.paymentService = paymentService;
    }

    public void createCustomer(String carSign, String customerName, String costumerCpf) {
        CustomerSaveRequestDto customerSaveRequestDto = CustomerSaveRequestDto.builder()
                .externalReference(carSign)
                .name(customerName)
                .cpfCnpj(costumerCpf)
                .build();

        customerService.createNewCustomer(customerSaveRequestDto);
    }

    public String getPaymentStatus(String carSign) {
        return paymentService.retrieveStatusOfAPayment(carSign).getStatus().name();
    }

    public String getPaymentID(String carSign) {

        ListPaymentsParameters requestParameters = ListPaymentsParameters.builder()
                .externalReference(carSign)
                .build();
        PaymentListResponseDto paymentList = paymentService.listPayments(requestParameters);

        if (paymentList == null || paymentList.getData() == null || paymentList.getData().isEmpty()) {
            System.out.println("Nenhum pagamento encontrado para a placa: " + carSign);
            return null;
        }

        PaymentGetResponseDto payment = paymentList.getData().get(0);
        return payment.getId();
    }

    //Criar fatura
    public void createInvoice(String carSign, String ownerName, double orderPrice, String expiryDate, String description, String type) {

        PaymentSaveRequestBillingType paymentType = determinePaymentType(type);
        PaymentSaveRequestDto paymentSaveRequestDto = PaymentSaveRequestDto.builder()
                .customer(getCustomerId(carSign))
                .dueDate(expiryDate)
                .value(orderPrice)
                .description(description)
                .billingType(paymentType)
                .build();
        paymentService.createNewPayment(paymentSaveRequestDto);
    }

    //Pegar id do customer pela placa
    public String getCustomerId(String carSign) {

        ListCustomersParameters requestParameters = ListCustomersParameters.builder()
                .externalReference(carSign)
                .build();
        CustomerListResponseDto response = customerService.listCustomers(requestParameters);
        return response.getData().stream()
                .filter(customer -> carSign.equals(customer.getExternalReference()))
                .map(CustomerGetResponseDto::getId)
                .findFirst()
                .orElse(null);
    }

    public List<Invoice> getAllPaymentsPendents() throws JsonProcessingException {

        List<Invoice> all = new ArrayList<>();
        ListPaymentsParameters parameters = ListPaymentsParameters.builder()
                .build();
        PaymentListResponseDto newPayment = paymentService.listPayments();
        System.out.println("TESTANDO: " + newPayment.getData());

        newPayment.getData().forEach(payment ->
                all.add(new Invoice(payment, customerService)));

        return all;
    }

    private PaymentSaveRequestBillingType determinePaymentType(String type) {
        return switch (type.toLowerCase()) {
            case "pix" -> PaymentSaveRequestBillingType.PIX;
            case "boleto" -> PaymentSaveRequestBillingType.BOLETO;
            default -> PaymentSaveRequestBillingType.UNDEFINED;

        };
    }

    public Customer getCustomerByID(String customerID) {
        CustomerGetResponseDto customerGetResponseDto = customerService.retrieveASingleCustomer(customerID);
        return new Customer(customerGetResponseDto);
    }

}

