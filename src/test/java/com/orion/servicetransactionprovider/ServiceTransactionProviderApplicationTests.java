package com.orion.servicetransactionprovider;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ServiceTransactionProviderApplicationTests {

    @Test
    void contextLoads() {
        ServiceTransactionProviderApplication app = new ServiceTransactionProviderApplication();
        assertNotNull(app);
        app.cleanup();
        assertNotNull(app);
    }

}
