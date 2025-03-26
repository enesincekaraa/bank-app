package com.enesincekara.account_service.dto;

import com.enesincekara.account_service.model.Account;

public class AccountConverter {

    public static AccountDto toDto(Account account) {
        return new AccountDto(
                account.getId(),
                account.getUserEmail(),
                account.getAccountType(),
                account.getBalance()
        );
    }


    public static Account toEntity(AccountDto accountDto) {
        Account account = new Account();
        account.setId(accountDto.id());
        account.setUserEmail(accountDto.userEmail());
        account.setAccountType(accountDto.accountType());
        account.setBalance(accountDto.balance());

        return account;
    }

    public static Account fromCreateToEntity(AccountCreateRequest request){
        Account account = new Account();
        account.setAccountType(request.accountType());
        account.setBalance(request.balance());
        return account;
    }
}
