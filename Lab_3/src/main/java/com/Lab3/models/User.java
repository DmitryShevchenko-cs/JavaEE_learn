package com.Lab3.models;
import com.Lab3.PasswordManager;

import java.time.LocalDate;

public class User {
    private String _name;
    private String _hashpassword;
    private String _email;
    private LocalDate _birthDate; // requires Java 8, should be configured in pom.xml
    private String _role;

    public User(){}

    public User(String _name, String _password, String _email, LocalDate _birthDate, String _role) {
        this._name = _name;
        this._hashpassword = PasswordManager.hashPassword(_password);;
        this._email = _email;
        this._birthDate = _birthDate;
        this._role = _role;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_hashpassword() {
        return _hashpassword;
    }

    public void set_password(String _password) {
        this._hashpassword = _password;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public LocalDate get_birthDate() {
        return _birthDate;
    }

    public void set_birthDate(LocalDate _birthDate) {
        this._birthDate = _birthDate;
    }

    public String getRole() {
        return _role;
    }
}