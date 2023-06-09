package com.mycompany.carRental.Controllers;

import com.mycompany.carRental.Entities.CarModel;
import com.mycompany.carRental.Entities.Rental;
import com.mycompany.carRental.Entities.User;
import com.mycompany.carRental.Services.CarModelService;
import com.mycompany.carRental.Services.RentalService;
import com.mycompany.carRental.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MainController {
    @Autowired private CarModelService carModelService;
    @Autowired private RentalService rentalService;
    @Autowired private UserService userService;

    @GetMapping("/index")
    public String showIndexPage() {
        return "index";
    }
    @GetMapping("/")
    public String showHomePage() {
        return "home";
    }
    @GetMapping("/reservation_form")
    public String showFormPage(Model model) {
        model.addAttribute("rental", new Rental());
        model.addAttribute("user", new User());
        List<CarModel> carModels = carModelService.listAll();
        model.addAttribute("carModels", carModels);
        model.addAttribute("pageTitle", "Add New Rental");
        return "reservation_form";
    }
    @PostMapping("/rentals/save")
    public String saveRental(Rental rental, RedirectAttributes redirectAttributes) {
        User user = rental.getUser();
        var user2 = userService.listAll().stream().filter(u -> u.getEmail().equals(user.getEmail())).findFirst();
        if (user2.isPresent())
            user.setId(user2.get().getId());
        var cars = rentalService.listAll().stream()
                .filter(c -> c.getCar().getInfoForUser().equals(rental.getCar().getInfoForUser())).toList();
        if(cars.size() > 0){
            var returnDates = cars.stream().filter(c ->
                 c.getReturnDate().isAfter(rental.getDateOfIssue())
            ).toList();
            if(returnDates.size() == 0){
                userService.save(user);
                redirectAttributes.addFlashAttribute("successMessage", "Rental successfully added.");
                rentalService.save(rental);
            }
            else {
                redirectAttributes.addFlashAttribute("successMessage", "This car is busy at this time.");
                return "redirect:/reservation_form";
            }
        }
        return "redirect:/";
    }
}
