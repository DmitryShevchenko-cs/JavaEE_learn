package com.mycompany.carRental.Services;

import com.mycompany.carRental.Entities.Car;
import com.mycompany.carRental.Entities.User;
import com.mycompany.carRental.Repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    @Autowired private CarRepository repo;

    public void save(Car car){
        repo.save(car);
    }

    public Car get(Integer id){
        var result = repo.findById(id);
        return result.get();
    }

    public void delete(Integer id){
        repo.deleteById(id);
    }
    public List<Car> listAll(){
        return (List<Car>) repo.findAll();
    }

}
