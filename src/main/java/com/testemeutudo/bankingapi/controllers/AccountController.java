package com.testemeutudo.bankingapi.controllers;

import com.testemeutudo.bankingapi.models.Account;
import com.testemeutudo.bankingapi.models.BankTransaction;
import com.testemeutudo.bankingapi.repos.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;

@Controller // This means that this class is a Controller
@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;


    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody String addNewAccount (@RequestParam String name
            , @RequestParam int cpf, @RequestParam double balance, @RequestParam Set<BankTransaction> payments, @RequestParam Set<BankTransaction> receive) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request


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
        // This returns a JSON or XML with the users
        return accountRepository.findAll();
    }

//    TODO: CONSULTAR SALDO
}