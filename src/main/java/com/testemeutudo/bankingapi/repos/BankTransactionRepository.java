package com.testemeutudo.bankingapi.repos;


import com.testemeutudo.bankingapi.models.BankTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankTransactionRepository extends JpaRepository<BankTransaction, Integer> {
}
