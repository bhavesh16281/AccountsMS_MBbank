package com.bhavesh16281.accounts.dto;

public record AccountsMessageDto(
    Long accountNumber,
    String customerName,
    String email,
    String phoneNumber
) {
}