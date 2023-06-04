package com.mycompany.carRental;

import com.mycompany.carRental.Entities.Rental;
import com.mycompany.carRental.Entities.User;
import com.mycompany.carRental.Repositories.CarModelRepository;
import com.mycompany.carRental.Repositories.RentalRepository;
import com.mycompany.carRental.Repositories.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback()
public class RentalRepositoryTest {
    @Autowired private UserRepository userRepo;
    @Autowired private CarModelRepository carModelRepo;
    @Autowired private RentalRepository rentalRepo;

    @Test
    public void TestAddNew(){
        Rental rent = new Rental();
        rent.setDateOfIssue( LocalDate.of(2023, 6, 3));
        rent.setReturnDate( LocalDate.of(2023, 6, 8));
        rent.setCar(carModelRepo.findById(2).get());
        rent.setUser(userRepo.findById(3).get());

        Rental savedRent = rentalRepo.save(rent);
        Assertions.assertThat(savedRent).isNotNull();
        Assertions.assertThat(savedRent.getId()).isGreaterThan(0);
    }

//    @Test
//    public void TestAddNew_AlreadyUsedEmail_ThrowException(){
//
//        Rental rent = new Rental();
//        rent.setDateOfIssue( LocalDate.of(2023, 6, 3));
//        rent.setReturnDate( LocalDate.of(2023, 6, 8));
//        rent.setCar(carModelRepo.findById(2).get());
//        rent.setUser(userRepo.findById(3).get());
//
//        Assertions.assertThatThrownBy(() -> {rentalRepo.save(rent);})
//                .isInstanceOf(Exception.class);
//    }

    @Test
    public void TestDelUser_UserFound_DeletedUser(){
        Rental user = rentalRepo.findById(2).get();
        rentalRepo.deleteById(user.getId());
        Assertions.assertThat(rentalRepo.findById(user.getId())).isEmpty();
    }
    @Test
    public void testListAll(){
        Iterable<Rental> alRentals = rentalRepo.findAll();
        Assertions.assertThat(alRentals).hasSizeGreaterThan(0);
        for (var rental : alRentals) {
            System.out.println(rental);
        }
    }

    @Test
    public void testUpdate(){
        var rentals = rentalRepo.findById(2).get();
        rentals.setDateOfIssue(LocalDate.of(2023, 9, 3));
        rentals.setReturnDate(LocalDate.of(2023, 9, 6));

        rentals = rentalRepo.save(rentals);

        Assertions.assertThat(rentals.getDateOfIssue()).isEqualTo(LocalDate.of(2023, 9, 3));
        Assertions.assertThat(rentals.getReturnDate()).isEqualTo(LocalDate.of(2023, 9, 6));
    }
}
