package com.mycompany.carRental.Repositories;

import com.mycompany.carRental.Entities.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Integer> {

}