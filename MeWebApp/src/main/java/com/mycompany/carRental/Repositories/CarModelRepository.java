package com.mycompany.carRental.Repositories;

import com.mycompany.carRental.Entities.CarModel;
import org.springframework.data.repository.CrudRepository;

public interface CarModelRepository extends CrudRepository<CarModel, Integer> {

}
