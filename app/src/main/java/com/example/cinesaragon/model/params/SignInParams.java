package com.example.cinesaragon.model.params;

public class SignInParams {

    final String username;
    final String password;

    public SignInParams(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
