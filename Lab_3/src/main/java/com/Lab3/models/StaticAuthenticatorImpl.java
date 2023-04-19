package com.Lab3.models;

import com.Lab3.models.User;
import com.Lab3.profile.Authenticator;
import com.Lab3.repositories.UsersRepository;
import com.Lab3.repositories.UsersRepositoryInMemoryImpl;

public class StaticAuthenticatorImpl implements Authenticator {

    public UsersRepository usersRepository;
    public StaticAuthenticatorImpl() {
        this.usersRepository = new UsersRepositoryInMemoryImpl();
    }
    @Override
    public User authenticateByUserName(String username, String password) {
        return usersRepository.findByNameAndPassword(username, password);
    }

    @Override
    public User authenticateByUserEmail(String email, String password) {
        return usersRepository.findByEmailAndPassword(email, password);
    }
}
