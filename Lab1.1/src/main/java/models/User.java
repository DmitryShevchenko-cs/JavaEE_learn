package models;
import java.time.LocalDate;

public class User {
    private String _name;
    private String _password;
    private String _email;
    private LocalDate _birthDate; // requires Java 8, should be configured in pom.xml


    public User(String _name, String _password, String _email, LocalDate _birthDate) {
        this._name = _name;
        this._password = _password;
        this._email = _email;
        this._birthDate = _birthDate;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
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
}