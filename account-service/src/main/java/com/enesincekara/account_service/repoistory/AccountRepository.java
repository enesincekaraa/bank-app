package com.enesincekara.account_service.repoistory;

import com.enesincekara.account_service.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findByUserEmail(String userEmail);
}
