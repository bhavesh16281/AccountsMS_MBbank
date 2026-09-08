package com.bhavesh16281.accounts.functions;

import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bhavesh16281.accounts.service.AccountsService;

@Configuration
public class AccountsFunction {
    
    private static final Logger logger = LoggerFactory.getLogger(AccountsFunction.class);

    @Bean 
    public Consumer<Long> updateCommunication(AccountsService accountsService) {
        return accountNumber -> {
            logger.info("Received account number from RabbitMQ: {}", accountNumber);
            accountsService.updateCommunicationSwitch(accountNumber);
        };
    }
}
