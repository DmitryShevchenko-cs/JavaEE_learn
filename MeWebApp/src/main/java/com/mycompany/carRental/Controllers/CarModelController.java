package com.mycompany.carRental.Controllers;

import com.mycompany.carRental.Entities.CarModel;
import com.mycompany.carRental.Services.CarModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CarModelController {
    @Autowired private CarModelService service;

    @GetMapping("/index/CarModels")
    public String showUserList(Model model){
        List<CarModel> listCarModels = service.listAll();
        model.addAttribute("listCarModels", listCarModels);
        return "CarModels";
    }

    @GetMapping("/index/CarModels/new")
    public String showNewForm(Model model){
        model.addAttribute("car_model", new CarModel());
        model.addAttribute("pageTitle", "Add New Car Model");
        return "CarModel_form";
    }

    @PostMapping("/index/CarModels/save")
    public String saveCar(CarModel carModel){
        service.save(carModel);
        return "redirect:/index/CarModels";
    }

    @GetMapping("/index/CarModels/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model){

        var carModel = service.get(id);
        model.addAttribute("car_model", carModel);
        model.addAttribute("pageTitle", "Edit car model (ID:" + id + ")");
        return "CarModel_form";

    }
    @GetMapping("/index/CarModels/delete/{id}")
    public String deleteCar(@PathVariable("id") Integer id){
        service.delete(id);
        return "redirect:/index/CarModels";
    }
}
