package com.bhavesh16281.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

    private String name;
    private String email;
    private String phone;
    private AccountsDTO accountsDTO;
}
