package com.techreturners.weatheripe.repository;

import com.techreturners.weatheripe.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
}
