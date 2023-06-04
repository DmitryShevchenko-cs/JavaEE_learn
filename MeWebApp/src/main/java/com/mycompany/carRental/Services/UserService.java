package com.mycompany.carRental.Services;

import com.mycompany.carRental.Entities.User;
import com.mycompany.carRental.Exceptions.UserNotFoundException;
import com.mycompany.carRental.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {
    @Autowired private UserRepository repo;
    public List<User> listAll(){
        return (List<User>) repo.findAll();
    }

    public void save(User user) {
        repo.save(user);
    }

    public User get(Integer id) throws UserNotFoundException {
        var result = repo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new UserNotFoundException("Could not find any user with ID" + id);
    }

    public void delete(Integer id) throws UserNotFoundException {
        repo.deleteById(id);
    }
}
