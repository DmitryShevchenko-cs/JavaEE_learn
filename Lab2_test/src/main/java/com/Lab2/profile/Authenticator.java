package com.Lab2.profile;

public interface Authenticator {

    public boolean authenticateByUserName(String userName, String password);
    public boolean authenticateByUserEmail(String email, String password);

}
