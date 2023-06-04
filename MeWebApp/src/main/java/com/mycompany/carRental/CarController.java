package com.mycompany.carRental;

import com.mycompany.carRental.Entities.Car;
import com.mycompany.carRental.Services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CarController {
    @Autowired private CarService service;
    @GetMapping("/cars")
    public String showUserCars(Model model){
        List<Car> listCars = service.listAll();
        model.addAttribute("listCars", listCars);
        return "cars";
    }
    @GetMapping("/cars/new")
    public String showNewForm(Model model){
        model.addAttribute("car", new Car());
        model.addAttribute("pageTitle", "Add New Car");
        return "car_form";
    }

    @PostMapping("/cars/save")
    public String saveCar(Car car){
        service.save(car);
        return "redirect:/cars";
    }

    @GetMapping("/cars/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model){

        var car = service.get(id);
        model.addAttribute("car", car);
        model.addAttribute("pageTitle", "Edit car (ID:" + id + ")");
        return "car_form";

    }
    @GetMapping("/cars/delete/{id}")
    public String deleteCar(@PathVariable("id") Integer id){
        service.delete(id);
        return "redirect:/cars";
    }

}
