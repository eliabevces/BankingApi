package com.testemeutudo.bankingapi.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    private String name;

    private String cpf;

    private double balance;

    @OneToMany(mappedBy="sender")
    @JsonManagedReference
    private Set<BankTransaction> payments;

    @OneToMany(mappedBy="receiver")
    @JsonManagedReference
    private Set<BankTransaction> receive;

    public Account() {}

    public Account(String name, String cpf, double balance) {
        this.name = name;
        this.cpf = cpf;
        this.balance = balance;
        this.payments = Collections.emptySet();
        this.receive = Collections.emptySet();
    }

    public int getAccountId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public double getBalance() {
        return balance;
    }

    public Set<BankTransaction> getPayments() {
        return payments;
    }

    public Set<BankTransaction> getReceive() {
        return receive;
    }

    public void setAccountId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setPayments(Set<BankTransaction> payments) {
        this.payments = payments;
    }

    public void setReceive(Set<BankTransaction> receive) {
        this.receive = receive;
    }
}
