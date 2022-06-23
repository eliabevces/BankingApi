package com.testemeutudo.bankingapi.models;

import java.time.LocalDate;
import javax.persistence.*;


@Entity
public class BankTransaction {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name="account_id", nullable=false)
    private Account sender;

    @ManyToOne
    @JoinColumn(name="account_id", nullable=false)
    private Account receiver;

    private LocalDate paymentDate;

    private double value;

    public BankTransaction() {}

    public BankTransaction(Account sender, Account receiver, LocalDate paymentDate, double value) {
        this.sender = sender;
        this.receiver = receiver;
        this.paymentDate = paymentDate;
        this.value = value;
    }

    public void executeTransaction() throws Exception {
        if(this.sender.getBalance() < this.value) {
            throw new Exception("This account doesn't have that kind of money");
        }
        double senderBalance = this.sender.getBalance();
        double receiverBalance = this.receiver.getBalance();

        try {
            this.sender.setBalance(senderBalance-value);
            this.receiver.setBalance(receiverBalance+value);
        }catch (Exception e){
            this.sender.setBalance(senderBalance);
            this.receiver.setBalance(receiverBalance);
            throw e;
        }
    }

    public int getAccountId() {
        return id;
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
        this.id = accountId;
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
