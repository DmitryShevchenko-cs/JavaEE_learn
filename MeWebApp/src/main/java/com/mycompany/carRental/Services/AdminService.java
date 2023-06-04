package com.mycompany.carRental.Services;

import com.mycompany.carRental.Entities.Admin;
import com.mycompany.carRental.Entities.Car;
import com.mycompany.carRental.Repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired private AdminRepository repo;

    public void save(Admin admin){
        repo.save(admin);
    }
    public Admin get(Integer id){
        var result = repo.findById(id);
        return result.get();
    }

    public void delete(Integer id){
        repo.deleteById(id);
    }

    public List<Admin> listAll(){
        return (List<Admin>) repo.findAll();
    }
}
