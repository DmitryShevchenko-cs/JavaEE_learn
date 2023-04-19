package com.Lab4.profile;

import com.Lab4.models.User;

import java.util.Objects;

public class AuthenticatorImpl implements Authenticator
{
    @Override
    public User authenticateByUserName(String username, String password) {
        final User account = ProfileTools.getUsersRepository().findByName(username);
        return isValidPassowrd(account, password) ? account : null;
    }

    @Override
    public User authenticateByUserEmail(String email, String password) {
        final User account = ProfileTools.getUsersRepository().findByEmail(email);
        return isValidPassowrd(account, password) ? account : null;
    }

    private static boolean isValidPassowrd(final User account, String password) {
        return account != null && Objects.equals(password, account.getPassword());
    }
}