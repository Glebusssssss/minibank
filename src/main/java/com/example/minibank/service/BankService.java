package com.example.minibank.service;

import com.example.minibank.model.*;
import com.example.minibank.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private TransactionRepository txRepo;

    private User currentUser;

    public String register(User user) {
        if (userRepo.findByUsername(user.getUsername()) != null)
            return "User exists";

        userRepo.save(user);
        return "Registered";
    }

    public String login(User user) {
        User found = userRepo.findByUsername(user.getUsername());

        if (found != null && found.getPassword().equals(user.getPassword())) {
            currentUser = found;
            return "Login successful";
        }
        return "Invalid login";
    }

    public String deposit(double amount) {
        if (currentUser == null) return "Not logged in";

        Transaction t = new Transaction();
        t.setUserId(currentUser.getId());
        t.setType("deposit");
        t.setAmount(amount);
        txRepo.save(t);

        return "Deposited " + amount;
    }

    public String withdraw(double amount) {
        if (currentUser == null) return "Not logged in";

        double balance = getBalanceValue();
        if (amount > balance) return "Insufficient funds";

        Transaction t = new Transaction();
        t.setUserId(currentUser.getId());
        t.setType("withdraw");
        t.setAmount(amount);
        txRepo.save(t);

        return "Withdrawn " + amount;
    }

    public double getBalanceValue() {
        List<Transaction> list = txRepo.findByUserId(currentUser.getId());

        double balance = 0;
        for (Transaction t : list) {
            if (t.getType().equals("deposit")) balance += t.getAmount();
            else balance -= t.getAmount();
        }
        return balance;
    }

    public String balance() {
        return "Balance: " + getBalanceValue();
    }

    public List<Transaction> history() {
        return txRepo.findByUserId(currentUser.getId());
    }
}