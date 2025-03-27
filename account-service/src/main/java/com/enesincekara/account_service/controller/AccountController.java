package com.enesincekara.account_service.controller;

import com.enesincekara.account_service.dto.AccountCreateRequest;
import com.enesincekara.account_service.dto.AccountDto;
import com.enesincekara.account_service.dto.AccountUpdateRequest;
import com.enesincekara.account_service.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public ResponseEntity<AccountDto> createAccount(@RequestBody @Valid AccountCreateRequest request) {

        return ResponseEntity.ok(accountService.createAccount(request));
    }

    @GetMapping("/me")
    public ResponseEntity<List<AccountDto>> getMyAccounts() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        List<AccountDto> accounts = accountService.getAccountsByUserEmail(email);
        return ResponseEntity.ok(accounts);
    }

    @PutMapping("/update-balance")
    public ResponseEntity<Void> updateBalance(
            @RequestParam String email,
            @RequestParam String accountType,
            @RequestParam BigDecimal amount
                                              ) {
        accountService.updateBalance(email,accountType,amount);
        return ResponseEntity.ok().build();
    }


}
