package com.Lab6.repositories;
import com.Lab6.models.User;
import com.Lab6.Dao.CRUD;

public interface UsersRepository extends CRUD<User>
{
    public User findByName(String name);
    public User findByEmail(String email);
}
