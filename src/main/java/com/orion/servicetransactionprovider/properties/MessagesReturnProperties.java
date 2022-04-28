package com.orion.servicetransactionprovider.properties;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@Accessors(chain = true)
@ConfigurationProperties("orion.transaction.proviver.message")
public class MessagesReturnProperties {

    private String error;
    private String messageok;
    private String statusok;
    private String messageparamtererror;
}
