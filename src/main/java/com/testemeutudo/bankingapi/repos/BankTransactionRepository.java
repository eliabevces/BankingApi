package com.testemeutudo.bankingapi.repos;


import com.testemeutudo.bankingapi.models.BankTransaction;
import org.springframework.data.repository.CrudRepository;

public interface BankTransactionRepository extends CrudRepository<BankTransaction, Integer> {
}
