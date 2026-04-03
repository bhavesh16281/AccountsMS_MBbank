package com.bhavesh16281.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountsDTO {

    private Long accountNumber;
    private String accountType;
    private String branchAddress;
}
