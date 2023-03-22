package com.techreturners.weatheripe.repository;

import com.techreturners.weatheripe.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    Optional<UserAccount> findByUserName(String username);

    boolean existsByUserName(String username);

    boolean existsByEmail(String email);
}
