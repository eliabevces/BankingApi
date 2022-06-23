package com.testemeutudo.bankingapi.repos;


import com.testemeutudo.bankingapi.models.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Integer> {
}
