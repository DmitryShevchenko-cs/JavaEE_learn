package com.Lab5.builders;

import com.Lab5.models.User;
import com.Lab5.profile.AccountType;

import java.time.LocalDate;

public class UserBuilder {
    private User _user;

    public UserBuilder() {
        _user = new User();
    }

    public UserBuilder(Integer id) {
        _user = new User(id);
    }

    public UserBuilder setRole(AccountType role) {
        _user.setRole(role); return this;
    }

    public UserBuilder setName(String name) {
        _user.setName(name); return this;
    }

    public UserBuilder setPassword(String password) {
        _user.setPassword(password); return this;
    }

    public UserBuilder setBirthDate(LocalDate birthDate) {
        _user.setBirthDate(birthDate); return this;
    }

    public UserBuilder setLoginDate(LocalDate loginDate) {
        _user.setLoginDate(loginDate); return this;
    }

    public UserBuilder setEmail(String email) {
        _user.setEmail(email); return this;
    }

    public User build() {
        return _user;
    }

    public UserBuilder setTax(Double tax) {
        _user.set_tax(tax); return this;
    }

}
