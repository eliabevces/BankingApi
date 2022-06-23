package com.testemeutudo.bankingapi.controllers;

import com.testemeutudo.bankingapi.models.Account;
import com.testemeutudo.bankingapi.models.BankTransaction;
import com.testemeutudo.bankingapi.repos.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
@RequestMapping(path="/account")
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;


    @PostMapping(path="/add")
    public @ResponseBody String addNewAccount (@RequestParam String name
            , @RequestParam int cpf, @RequestParam double balance, @RequestParam Set<BankTransaction> payments, @RequestParam Set<BankTransaction> receive) {

        Account account = new Account();
        account.setName(name);
        account.setCpf(cpf);
        account.setBalance(balance);
        account.setPayments(payments);
        account.setReceive(receive);
        accountRepository.save(account);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Account> getAllUsers() {
        return accountRepository.findAll();
    }

    @GetMapping(value="/balance/{id}")
    public @ResponseBody String getBalance(@PathVariable("id") int accountid){
        try {
            Account a = accountRepository.getReferenceById(accountid);
            return ("Balance = $" + a.getBalance());
        }catch (Exception e){
            return "Account not found";
        }
    }



}