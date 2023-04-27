package com.Lab5.repositories;
import com.Lab5.models.User;
import com.Lab5.Dao.CRUD;

public interface UsersRepository extends CRUD<User>
{
    public User findByName(String name);
    public User findByEmail(String email);
}
