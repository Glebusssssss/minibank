package com.example.minibank.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users") // ✅ FIX: avoid SQL keyword "user"
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    // ✅ REQUIRED by Spring
    public User() {}

    // (optional but good)
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getId() { return id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}