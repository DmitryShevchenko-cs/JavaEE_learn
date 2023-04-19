package com.Lab3.profile;

import com.Lab3.PasswordManager;
import com.Lab3.models.User;

public class AuthenticatorImpl implements Authenticator
{
    @Override
    public User authenticateByUserName(String username, String password) {
        final User account = ProfileTools.getUsersRepository().findByNameAndPassword(username, password);
        return isValidPassowrd(account, password) ? account : null;
    }

    @Override
    public User authenticateByUserEmail(String email, String password) {
        final User account = ProfileTools.getUsersRepository().findByEmailAndPassword(email, password);
        return isValidPassowrd(account, password) ? account : null;
    }

    private static boolean isValidPassowrd(final User account, String password) {
        return account != null && PasswordManager.checkPassword(password, account.get_hashpassword());
    }
}