package com.enesincekara.user_service.repository;

import com.enesincekara.user_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    Optional<User>  getUserByEmail(String email);
}
