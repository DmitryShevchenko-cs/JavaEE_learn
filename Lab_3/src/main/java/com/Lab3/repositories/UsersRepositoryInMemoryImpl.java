package com.Lab3.repositories;

import com.Lab3.mocks.InMemoryStorage;
import com.Lab3.models.User;

import java.util.List;

public class UsersRepositoryInMemoryImpl implements UsersRepository
{
    public List<User> findByName(String name) { return InMemoryStorage.getInstance().getByName(name);}

    public User findByEmailAndPassword(String email, String password) {
        return InMemoryStorage.getInstance().findByEmailAndPassword(email, password);
    }

    public User findByNameAndPassword(String name, String password) {
        return InMemoryStorage.getInstance().findByNameAndPassword(name, password);
    }

    public List<User> findAll() {
        return InMemoryStorage.getInstance().getAllUsers();
    }

    public void set_user(User user) {
        InMemoryStorage.getInstance().set_user(user);
    }
}
