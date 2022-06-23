package com.testemeutudo.bankingapi.models;

import java.time.LocalDate;
import javax.persistence.*;


@Entity
public class BankTransaction {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int accountId;

    @ManyToOne
    @JoinColumn(name="accountId", nullable=false)
    private Account sender;

    @ManyToOne
    @JoinColumn(name="accountId", nullable=false)
    private Account receiver;

    private LocalDate paymentDate;

    public BankTransaction() {}

    public BankTransaction(Account sender, Account receiver, LocalDate paymentDate) {
        this.sender = sender;
        this.receiver = receiver;
        this.paymentDate = paymentDate;
    }

    public int getAccountId() {
        return accountId;
    }

    public Account getSender() {
        return sender;
    }

    public Account getReceiver() {
        return receiver;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public void setSender(Account sender) {
        this.sender = sender;
    }

    public void setReceiver(Account receiver) {
        this.receiver = receiver;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }
}
