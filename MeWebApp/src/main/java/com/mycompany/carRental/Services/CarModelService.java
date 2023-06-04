package com.mycompany.carRental.Services;

import com.mycompany.carRental.Entities.CarModel;
import com.mycompany.carRental.Repositories.CarModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarModelService {
    @Autowired private CarModelRepository repo;

    public void save(CarModel carModel){
        repo.save(carModel);
    };

    public CarModel get(Integer id){
        var result = repo.findById(id);
        return result.get();
    }
    public void delete(Integer id){
        repo.deleteById(id);
    }

    public List<CarModel> listAll(){
        return (List<CarModel>) repo.findAll();
    }
}