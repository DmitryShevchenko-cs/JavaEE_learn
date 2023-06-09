package com.mycompany.carRental.Controllers;

import com.mycompany.carRental.Entities.Rental;
import com.mycompany.carRental.Entities.User;
import com.mycompany.carRental.Exceptions.UserNotFoundException;
import com.mycompany.carRental.Services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class RentalController {
    @Autowired private RentalService service;

    @GetMapping("/index/rentals")
    public String showUserList(Model model){
        List<Rental> listRental = service.listAll();
        model.addAttribute("listRental", listRental);
        return "rentals";
    }
    @GetMapping("/index/rentals/new")
    public String showNewForm(Model model){
        model.addAttribute("rental", new Rental());
        model.addAttribute("pageTitle", "Add New Rental");
        return "rental_form";
    }
    @PostMapping("/rentals/saveRental")
    public String saveUser(Rental rental){
        service.save(rental);
        return "redirect:/index/rentals";
    }
    @GetMapping("/rentals/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model){
        var rental = service.get(id);
        model.addAttribute("rental", rental);
        model.addAttribute("pageTitle", "Edit rental (ID:" + id + ")");
        return "rental_form";
    }
    @GetMapping("/index/rentals/delete/{id}")
    public String deleteCar(@PathVariable("id") Integer id){
        service.delete(id);
        return "redirect:/index/rentals";
    }
}