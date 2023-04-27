package com.Lab5.profile;

import com.Lab5.models.User;

import javax.sql.DataSource;
import java.util.Objects;

public class AuthenticatorImpl implements Authenticator
{
    DataSource ds;
    public AuthenticatorImpl(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public User authenticateByUserName(String username, String password) {
        final User account = ProfileTools.getUsersRepository(ds).findByName(username);
        return isValidPassowrd(account, password) ? account : null;
    }

    @Override
    public User authenticateByUserEmail(String email, String password) {
        final User account = ProfileTools.getUsersRepository(ds).findByEmail(email);
        return isValidPassowrd(account, password) ? account : null;
    }

    private static boolean isValidPassowrd(final User account, String password) {
        return account != null && Objects.equals(password, account.getPassword());
    }
}