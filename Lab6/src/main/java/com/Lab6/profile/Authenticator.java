package com.Lab6.profile;

import com.Lab6.models.User;

public interface Authenticator {

    public User authenticateByUserName(String userName, String password);
    public User authenticateByUserEmail(String email, String password);

}
