package com.Lab4.repositories;
import com.Lab4.models.User;
import com.Lab4.Dao.CRUD;
import java.util.List;

public interface UsersRepository extends CRUD<User>
{
    public User findByName(String name);
    public User findByEmail(String email);
}
