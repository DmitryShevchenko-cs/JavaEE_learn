package com.Lab5.profile;

import com.Lab5.models.User;

public interface Authenticator {

    public User authenticateByUserName(String userName, String password);
    public User authenticateByUserEmail(String email, String password);

}
