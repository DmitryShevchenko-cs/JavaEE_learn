package com.Lab4.models;
import com.Lab4.profile.AccountType;
import java.io.Serializable;
import java.time.LocalDate;

public class User implements HasId, Serializable {
    private static final long serialVersionUID = 1L;
    private Integer _id;
    private String _name;
    private String _password;
    private String _email;
    private LocalDate _loginDate;
    private LocalDate _birthDate;
    private AccountType _role;

    public User() {
    }  // required by java-bean components

    public User(AccountType role, String name, String password, String email) {
        this(null, role, name, password, email, null, null);
    }

    public User(Integer id, AccountType role, String name, String password, String email,
                LocalDate birthDate, LocalDate loginDate) {
        _id = id;
        _role = role;
        _name = name;
        _password = password;
        _email = email;
        _birthDate = birthDate;
        _loginDate = loginDate;
    }

    public AccountType getRole() {
        return _role;
    }

    public void setRole(AccountType role) {
        _role = role;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public String getPassword() {
        return _password;
    }

    public void setPassword(String password) {
        _password = password;
    }

    public LocalDate getBirthDate() {
        return _birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        _birthDate = birthDate;
    }

    public LocalDate getLoginDate() {
        return _loginDate;
    }

    public void setLoginDate(LocalDate loginDate) {
        _loginDate = loginDate;
    }

    public String getEmail() {
        return _email;
    }

    public void setEmail(String email) {
        _email = email;
    }

    public Integer getId() {
        return _id;
    }

    public void setId(Integer id) {
        _id = id;
    }

    @Override
    public int hashCode() {
        int result = 1;
        final int prime = 31;
        result = prime * result + ((_email == null) ? 0 : _email.hashCode());
        result = prime * result + ((_id == null) ? 0 : _id.hashCode());
        result = prime * result + ((_loginDate == null) ? 0 : _loginDate.hashCode());
        result = prime * result + ((_name == null) ? 0 : _name.hashCode());
        result = prime * result + ((_password == null) ? 0 : _password.hashCode());
        result = prime * result + ((_role == null) ? 0 : _role.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Person [id=" + _id
                + ", email=" + _email
                + ", name=" + _name
                + ", password=" + _password
                + ", loginDate=" + _loginDate
                + ", role=" + _role + "]";
    }
    /* User.java (продолжение) */

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof User))
            return false;
        User other = (User) obj;
        if (_email == null)
            if (other._email != null)
                return false;
            else if (!_email.equals(other._email))
                return false;

        if (_id == null)
            if (other._id != null)
                return false;
            else if (!_id.equals(other._id))
                return false;

        if (_loginDate == null)
            if (other._loginDate != null)
                return false;
            else if (!_loginDate.equals(other._loginDate))
                return false;

        if (_name == null)
            if (other._name != null)
                return false;
            else if (!_name.equals(other._name))
                return false;

        if (_password == null)
            if (other._password != null)
                return false;
            else if (!_password.equals(other._password))
                return false;

        if (_role != other._role)
            return false;

        return true;
    }
}
