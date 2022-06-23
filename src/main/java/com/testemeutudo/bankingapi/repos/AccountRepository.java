package com.testemeutudo.bankingapi.repos;


import com.testemeutudo.bankingapi.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
}
