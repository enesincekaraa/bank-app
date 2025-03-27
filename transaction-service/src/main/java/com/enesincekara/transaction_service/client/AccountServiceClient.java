package com.enesincekara.transaction_service.client;

import com.enesincekara.transaction_service.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(
        name = "account-service",
        configuration = FeignClientConfig.class
)
public interface AccountServiceClient {

    @PutMapping("/api/v1/account/update-balance")
    ResponseEntity<Void> updateBalance(
            @RequestParam("email") String email,
            @RequestParam("accountType") String accountType,
            @RequestParam("amount") BigDecimal amount
    );
}
