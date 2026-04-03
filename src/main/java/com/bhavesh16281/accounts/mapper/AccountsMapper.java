package com.bhavesh16281.accounts.mapper;

import com.bhavesh16281.accounts.dto.AccountsDTO;
import com.bhavesh16281.accounts.entity.Accounts;

public class AccountsMapper {

    public static AccountsDTO mapToAccountsDto(Accounts accounts, AccountsDTO accountsDto) {

        accountsDto.setAccountNumber(accounts.getAccountNumber());
        accountsDto.setAccountType(accounts.getAccountType());
        accountsDto.setBranchAddress(accounts.getBranchAddress());

        return accountsDto;

    }

    public static Accounts mapToAccounts(AccountsDTO accountsDto, Accounts accounts) {

        accounts.setAccountNumber(accountsDto.getAccountNumber());
        accounts.setAccountType(accountsDto.getAccountType());
        accounts.setBranchAddress(accountsDto.getBranchAddress());

        return accounts;
    }
}
