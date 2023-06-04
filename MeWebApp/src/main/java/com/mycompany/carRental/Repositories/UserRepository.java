package com.mycompany.carRental.Repositories;

import com.mycompany.carRental.Entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

}
