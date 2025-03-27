package com.enesincekara.account_service.service.impl;

import com.enesincekara.account_service.client.UserServiceClient;
import com.enesincekara.account_service.dto.AccountConverter;
import com.enesincekara.account_service.dto.AccountCreateRequest;
import com.enesincekara.account_service.dto.AccountDto;
import com.enesincekara.account_service.model.Account;
import com.enesincekara.account_service.repoistory.AccountRepository;
import com.enesincekara.account_service.service.AccountService;
import feign.FeignException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final UserServiceClient userServiceClient;

    public AccountServiceImpl(AccountRepository accountRepository, UserServiceClient userServiceClient) {
        this.accountRepository = accountRepository;
        this.userServiceClient = userServiceClient;
    }

    @Override
    public AccountDto createAccount(AccountCreateRequest request) {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();


        try {
            userServiceClient.userExists(userEmail);
        }catch (FeignException.NotFound e){
            throw new RuntimeException("User not found : " + userEmail);
        }
        Account account = new Account();
        account.setUserEmail(userEmail);
        account.setAccountType(request.accountType());
        account.setBalance(request.balance());

        return AccountConverter.toDto(accountRepository.save(account));
    }



    @Override
    public List<AccountDto> getAccountsByUserEmail(String email) {
        return accountRepository.findByUserEmail(email)
                .stream()
                .map(AccountConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void updateBalance(String email, String accountType, BigDecimal amount) {
        Account account = accountRepository.findByUserEmail(email)
                .stream()
                .filter(a -> a.getAccountType().equalsIgnoreCase(accountType))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Account not found"));

        BigDecimal newBalance = account.getBalance().add(amount);

        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Account balance is negative");
        }

        account.setBalance(newBalance);
        accountRepository.save(account);
    }

}
