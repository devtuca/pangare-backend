package com.seguradora.paocomovo.config;

import com.asaas.apisdk.AsaasSdk;
import com.asaas.apisdk.config.ApiKeyAuthConfig;
import com.asaas.apisdk.config.AsaasSdkConfig;
import com.asaas.apisdk.exceptions.ApiError;
import com.asaas.apisdk.http.Environment;
import com.asaas.apisdk.services.CustomerService;
import com.asaas.apisdk.services.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AsaasConfig {

    private String asaasApiKey = "$aact_hmlg_000MzkwODA2MWY2OGM3MWRlMDU2NWM3MzJlNzZmNGZhZGY6OmU1Nzg0ODE0LTViZGItNDE4My04ZTRjLTc4ZjI5NDdkYmFhMDo6JGFhY2hfMWYwODY3Y2EtZjhjZC00ZGRkLTg5ZjUtYTYzZmQzZDdkMTY3";

    @Bean
    public AsaasSdk asaasSdk() {
        try {
            AsaasSdkConfig config = AsaasSdkConfig.builder()
                    .apiKeyAuthConfig(ApiKeyAuthConfig.builder()
                            .apiKey(asaasApiKey)
                            .build())
                    .build();

            config.setEnvironment(Environment.SANDBOX); // ou Environment.DEFAULT
            System.out.println(("[ASAAAS] Connected"));
            return new AsaasSdk(config);
        } catch (ApiError e) {
            e.printStackTrace();
        }
        return null;
    }

    @Bean
    public CustomerService customerService(AsaasSdk asaasSdk) {
        return asaasSdk.customer;
    }

    @Bean
    public PaymentService paymentService(AsaasSdk asaasSdk) {
        return asaasSdk.payment;
    }
}
