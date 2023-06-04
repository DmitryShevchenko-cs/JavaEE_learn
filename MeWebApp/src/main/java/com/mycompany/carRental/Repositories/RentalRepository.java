package com.mycompany.carRental.Repositories;

import com.mycompany.carRental.Entities.CarModel;
import com.mycompany.carRental.Entities.Rental;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RentalRepository extends CrudRepository<Rental, Integer> {
//
}