package com.testemeutudo.bankingapi.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import javax.persistence.*;


@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class BankTransaction {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Account sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setSender(Account sender) {
        this.sender = sender;
    }

    public void setReceiver(Account receiver) {
        this.receiver = receiver;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + "\"" +  id + "\"" +
                ", \"sender\":" + sender.getAccountId() +
                ", \"receiver\":" + receiver.getAccountId() +
                ", \"paymentDate\":" + "\"" + paymentDate + "\"" +
                ", \"value\":" + value +
                '}';
    }
}
