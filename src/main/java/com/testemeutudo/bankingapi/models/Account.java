package com.testemeutudo.bankingapi.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    private String name;

    private int cpf;

    private double balance;

    @OneToMany(mappedBy="sender")
    private Set<BankTransaction> payments;

    @OneToMany(mappedBy="receiver")
    private Set<BankTransaction> receive;

    public Account() {}

    public Account(String name, int cpf, double balance, Set<BankTransaction> payments, Set<BankTransaction> receive) {
        this.name = name;
        this.cpf = cpf;
        this.balance = balance;
        this.payments = payments;
        this.receive = receive;
    }

    public int getAccountId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCpf() {
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

    public void setCpf(int cpf) {
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
