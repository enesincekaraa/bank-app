package com.enesincekara.account_service.client;

import com.enesincekara.account_service.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "user-service",
        configuration = FeignClientConfig.class
)
public interface UserServiceClient {

    @GetMapping(path = "/api/v1/user/exists")
    ResponseEntity<Void> userExists(@RequestParam("email") String email);


}
