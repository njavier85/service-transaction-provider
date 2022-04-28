package com.orion.servicetransactionprovider;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import javax.annotation.PreDestroy;
import java.time.Instant;

@SpringBootApplication
@ConfigurationPropertiesScan
@Slf4j
public class ServiceTransactionProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceTransactionProviderApplication.class, args);
    }

    @PreDestroy
    public void cleanup() {
        log.info("service-risk-event-manager is being terminated at {}", Instant.now());
    }

}
