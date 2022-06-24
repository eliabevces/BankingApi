package com.testemeutudo.bankingapi.controllers;

import com.testemeutudo.bankingapi.models.Account;

import com.testemeutudo.bankingapi.repos.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping(path="/account")
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;


    @PostMapping(path="/add")
    public @ResponseBody Account addNewAccount (@RequestParam String name
            , @RequestParam String cpf, @RequestParam double balance) {

        Account account = new Account(name,cpf,balance);
        accountRepository.save(account);
        return account;
    }

    @GetMapping(path="/", produces = "application/json")
    public @ResponseBody ResponseEntity<List<Account>> getAllUsers() {
        return new ResponseEntity<>(
                accountRepository.findAll(),
                HttpStatus.OK);
    }

    @GetMapping(value="/balance/{id}")
    public @ResponseBody ResponseEntity<Double> getBalance(@PathVariable("id") int accountid){
        return new ResponseEntity<>(
                accountRepository.getReferenceById(accountid).getBalance(),
                HttpStatus.OK);
    }

}