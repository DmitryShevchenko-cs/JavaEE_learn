package com.Lab4.profile;

import com.Lab4.models.User;

public interface Authenticator {

    public User authenticateByUserName(String userName, String password);
    public User authenticateByUserEmail(String email, String password);

}
