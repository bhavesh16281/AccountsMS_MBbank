package com.bhavesh16281.accounts.service;

import com.bhavesh16281.accounts.dto.CustomerDTO;
import com.bhavesh16281.accounts.repository.AccountsRepository;
import com.bhavesh16281.accounts.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements AccountsService {

    @Autowired
    private AccountsRepository accountsRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDTO customerDTO) {

    }
}
