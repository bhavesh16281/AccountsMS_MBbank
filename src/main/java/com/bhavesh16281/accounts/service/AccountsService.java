package com.bhavesh16281.accounts.service;

import com.bhavesh16281.accounts.dto.CustomerDTO;
import com.bhavesh16281.accounts.dto.CustomerDetailsDto;

public interface AccountsService {

    void createAccount(CustomerDTO customerDTO);
    CustomerDTO getCustomerByPhone(String phone);
    boolean updateAccount(CustomerDTO customerDTO);
    boolean deleteAccount(String phone);
    CustomerDetailsDto getCustomerDetailsByPhone(String correlationId,String phone);
    boolean updateCommunicationSwitch(Long accountNumber);
}
