package com.Lab2.repositories;

import com.Lab2.models.User;

import java.util.List;

public interface UsersRepository
{
    List<User> findByName(String name);

    User findByEmailAndPassword(String email, String password);
    User findByNameAndPassword(String name, String password);

    List<User> findAll();

    void set_user(User user);
}