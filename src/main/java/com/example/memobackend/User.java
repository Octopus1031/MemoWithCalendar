package com.example.memobackend;

public class User {
    final private String id;
    final private String account;
    final private String name;

    public User(String id, String name, String account) {
        this.id = id;
        this.name = name;
        this.account = account;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public String getEmail() {
        return account;
    }
}
