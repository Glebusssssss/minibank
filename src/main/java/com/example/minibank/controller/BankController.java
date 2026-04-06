package com.example.minibank.controller;

import com.example.minibank.model.*;
import com.example.minibank.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BankController {

    @Autowired
    private BankService service;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        return service.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return service.login(user);
    }

    @PostMapping("/deposit")
    public String deposit(@RequestParam double amount) {
        return service.deposit(amount);
    }

    @PostMapping("/withdraw")
    public String withdraw(@RequestParam double amount) {
        return service.withdraw(amount);
    }

    @GetMapping("/balance")
    public String balance() {
        return service.balance();
    }

    @GetMapping("/history")
    public List<Transaction> history() {
        return service.history();
    }
}