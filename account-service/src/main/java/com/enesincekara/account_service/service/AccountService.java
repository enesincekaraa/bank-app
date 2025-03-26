package com.enesincekara.account_service.service;

import com.enesincekara.account_service.dto.AccountCreateRequest;
import com.enesincekara.account_service.dto.AccountDto;

import java.util.List;

public interface AccountService {

    AccountDto createAccount(AccountCreateRequest request);
    List<AccountDto> getAccountsByUserEmail(String email);

//    List<AccountDto> getAll();
//    AccountDto getById(Long id);
//    void delete(Long id);


}
