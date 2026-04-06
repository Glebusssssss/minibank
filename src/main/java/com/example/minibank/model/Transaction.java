package com.example.minibank.model;

import jakarta.persistence.*;

@Entity
@Table(name = "transactions") // ✅ good practice
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String type;
    private double amount;

    // ✅ REQUIRED by Spring
    public Transaction() {}

    // (optional constructor)
    public Transaction(Long userId, String type, double amount) {
        this.userId = userId;
        this.type = type;
        this.amount = amount;
    }

    public Long getId() { return id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
}