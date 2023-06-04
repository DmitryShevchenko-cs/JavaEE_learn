package com.mycompany.carRental.Services;

import com.mycompany.carRental.Entities.Rental;
import com.mycompany.carRental.Exceptions.UserNotFoundException;
import com.mycompany.carRental.Repositories.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalService {
    @Autowired
    private RentalRepository repo;

    public List<Rental> listAll(){
        return (List<Rental>) repo.findAll();
    }

    public void save(Rental rental) {
        repo.save(rental);
    }

    public Rental get(Integer id) {
        var result = repo.findById(id);
        return result.get();
    }

    public void delete(Integer id){
        repo.deleteById(id);
    }

}
