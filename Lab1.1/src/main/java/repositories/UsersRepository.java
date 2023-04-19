package repositories;

import models.User;

import java.util.List;

public interface UsersRepository
{
    List<User> findByName(String name);
    List<User> findAll();

    void set_user(User user);
}