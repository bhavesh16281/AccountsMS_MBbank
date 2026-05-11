package com.bhavesh16281.accounts.config;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    public static final String SPRING_CLOUD_BUS_EXCHANGE = "springCloudBus";

    /**
     * Create a Fanout exchange for Spring Cloud Bus
     * This allows the bus to broadcast config changes to all connected services
     */
    @Bean
    public FanoutExchange springCloudBusExchange() {
        return new FanoutExchange(SPRING_CLOUD_BUS_EXCHANGE, true, false);
    }
}
