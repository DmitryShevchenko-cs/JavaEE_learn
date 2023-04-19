package com.Lab2.profile;

import com.Lab2.models.User;
import com.Lab2.repositories.UsersRepository;
import com.Lab2.repositories.UsersRepositoryInMemoryImpl;

import java.util.List;

public class AuthenticatorImpl implements Authenticator{

    public UsersRepository usersRepository;
    public AuthenticatorImpl() {
        this.usersRepository = new UsersRepositoryInMemoryImpl();
    }
    @Override
    public boolean authenticateByUserName(String username, String password) {
        User loginUser = usersRepository.findByNameAndPassword(username, password);
        return loginUser != null;
    }

    @Override
    public boolean authenticateByUserEmail(String email, String password) {
        User loginUser = usersRepository.findByEmailAndPassword(email, password);
        return loginUser != null;
    }
}
