package com.enesincekara.account_service.service.impl;

import com.enesincekara.account_service.dto.AccountConverter;
import com.enesincekara.account_service.dto.AccountCreateRequest;
import com.enesincekara.account_service.dto.AccountDto;
import com.enesincekara.account_service.model.Account;
import com.enesincekara.account_service.repoistory.AccountRepository;
import com.enesincekara.account_service.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountCreateRequest request) {
        Account account = AccountConverter.fromCreateToEntity(request);
        Account saved = accountRepository.save(account);
        return AccountConverter.toDto(account);
    }

    @Override
    public List<AccountDto> getAll() {
        return accountRepository.findAll()
                .stream()
                .map(AccountConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AccountDto getById(Long id) {
        Account account = findById(id);
        return AccountConverter.toDto(account);
    }

    @Override
    public void delete(Long id) {
        Account account =findById(id);
        accountRepository.delete(account);
    }

    @Override
    public List<AccountDto> getAccountsByUserEmail(String email) {
        return accountRepository.findByUserEmail(email)
                .stream()
                .map(AccountConverter::toDto)
                .collect(Collectors.toList());
    }


    public Account findById(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account not found with id: " + id ));
        return account;
    }
}
