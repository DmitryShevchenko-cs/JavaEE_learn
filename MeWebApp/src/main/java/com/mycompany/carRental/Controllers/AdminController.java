package com.mycompany.carRental.Controllers;

import com.mycompany.carRental.Entities.Admin;
import com.mycompany.carRental.Services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private AdminService service;

    @GetMapping("/index/admins")
    public String showUserCars(Model model){
        List<Admin> listAdmins = service.listAll();
        model.addAttribute("listAdmins", listAdmins);
        return "admins";
    }
    @GetMapping("/index/admins/new")
    public String showNewForm(Model model){
        model.addAttribute("admin", new Admin());
        model.addAttribute("pageTitle", "Add New Admin");
        return "admin_form";
    }

    @PostMapping("/index/admins/save")
    public String saveCar(Admin admin){
        service.save(admin);
        return "redirect:/index/admins";
    }

    @GetMapping("/index/admins/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model){

        var admin = service.get(id);
        model.addAttribute("admin", admin);
        model.addAttribute("pageTitle", "Edit admin (ID:" + id + ")");
        return "admin_form";

    }
    @GetMapping("/index/admins/delete/{id}")
    public String deleteCar(@PathVariable("id") Integer id){
        service.delete(id);
        return "redirect:/index/admins";
    }
}
