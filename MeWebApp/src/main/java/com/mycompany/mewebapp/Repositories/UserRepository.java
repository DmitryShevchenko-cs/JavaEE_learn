package com.mycompany.mewebapp.Repositories;

import com.mycompany.mewebapp.Entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    public Long countById(Integer id);
}
