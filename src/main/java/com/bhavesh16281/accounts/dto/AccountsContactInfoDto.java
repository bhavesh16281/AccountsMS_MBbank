package com.bhavesh16281.accounts.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "accounts")
public record AccountsContactInfoDto(String message, Map<String,String> contactDetails) {

}
