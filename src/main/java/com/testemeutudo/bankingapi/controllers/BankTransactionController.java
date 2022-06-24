package com.testemeutudo.bankingapi.controllers;


import com.testemeutudo.bankingapi.models.Account;
import com.testemeutudo.bankingapi.models.BankTransaction;
import com.testemeutudo.bankingapi.repos.AccountRepository;
import com.testemeutudo.bankingapi.repos.BankTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path="/transaction")
public class BankTransactionController {

    @Autowired
    private BankTransactionRepository bankTransactionRepository;
    @Autowired
    private AccountRepository accountRepository;

    @PostMapping(path="/add")
    public @ResponseBody List<BankTransaction> addNewTransaction (@RequestParam int senderId, @RequestParam int receiverId, @RequestParam double value, @RequestParam int portions) throws Exception {
        Account sender = accountRepository.getReferenceById(senderId);
        Account receiver = accountRepository.getReferenceById(receiverId);
        List<BankTransaction> transactions = new ArrayList<>();

        for (int i = 0; i < portions; i++) {
            BankTransaction transaction = new BankTransaction(sender, receiver, LocalDate.now().plusMonths(i), value /portions);
            transactions.add(transaction);
            if (transaction.getPaymentDate().compareTo(LocalDate.now()) == 0){
                transaction.executeTransaction();
            }
            bankTransactionRepository.save(transaction);
        }
        return transactions; //Returns the whole value but not the real transaction saved
    }

    @PostMapping(path="/revert")
    public @ResponseBody String revertTransaction (@RequestParam int id) throws Exception {

        BankTransaction transaction = bankTransactionRepository.getReferenceById(id);
        Account newSender = transaction.getReceiver();
        transaction.setReceiver(transaction.getSender());
        transaction.setSender(newSender);
        transaction.executeTransaction();
        bankTransactionRepository.delete(transaction);
        return "Transaction reversed";
    }

    @GetMapping(path="/", produces = "application/json")
    public @ResponseBody ResponseEntity<String> getAllTransactions() {
        List<BankTransaction> transactions = bankTransactionRepository.findAll();
        StringBuilder transactionList = new StringBuilder("[ ");

        for (int i = 0; i < transactions.size(); i++) {
            BankTransaction transaction = transactions.get(i);
            String t = transaction.toString();
            if(i == 0){
                transactionList.append(t);
            }else transactionList.append(", ").append(t);
        }
        transactionList.append(" ]");

        return new ResponseEntity<>(
                transactionList.toString(),
                HttpStatus.OK);

    }

    @GetMapping(path="/{id}", produces = "application/json")
    public @ResponseBody BankTransaction getAllTransactions(@PathVariable("id") int id) {
        System.out.println(bankTransactionRepository.getReferenceById(id));
        return bankTransactionRepository.getReferenceById(id);
    }



}
