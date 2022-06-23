package com.testemeutudo.bankingapi.controllers;


import com.testemeutudo.bankingapi.models.Account;
import com.testemeutudo.bankingapi.models.BankTransaction;
import com.testemeutudo.bankingapi.repos.BankTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;

@Controller
@RequestMapping(path="/transaction")
public class BankTransactionController {

    @Autowired
    private BankTransactionRepository bankTransactionRepository;

    @PostMapping(path="/add")
    public @ResponseBody String addNewTransaction (@RequestParam Account sender, @RequestParam Account receiver, @RequestParam LocalDate paymentDate, @RequestParam double value) throws Exception {

        BankTransaction transaction = new BankTransaction(sender, receiver, paymentDate, value);
        if (paymentDate == LocalDate.now()){
            transaction.executeTransaction();
        }
        bankTransactionRepository.save(transaction);
        return "Saved";
    }



}
