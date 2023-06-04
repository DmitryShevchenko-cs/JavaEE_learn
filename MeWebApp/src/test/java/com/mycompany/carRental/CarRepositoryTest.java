package com.mycompany.carRental;

import com.mycompany.carRental.Entities.Car;
import com.mycompany.carRental.Entities.User;
import com.mycompany.carRental.Repositories.CarRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback()
public class CarRepositoryTest {
    @Autowired private CarRepository repo;

    @Test
    public void TestAddNew(){
        var car = new Car();
        car.setCarClass("Sport car");
        car.setName("Nissan");
        car.setModel("370Z");

        var savedCar= repo.save(car);
        Assertions.assertThat(savedCar).isNotNull();
        Assertions.assertThat(savedCar.getId()).isGreaterThan(0);
    }

    @Test
    public void TestAddNew_AlreadyUsedEmail_ThrowException(){

        var car = new Car();
        car.setCarClass("Sport car");
        car.setName("Nissan");
        car.setModel("GTR");

        Assertions.assertThatThrownBy(() -> {repo.save(car);})
                .isInstanceOf(Exception.class);
    }

    @Test
    public void testListAll(){
        Iterable<Car> allCars = repo.findAll();
        Assertions.assertThat(allCars).hasSizeGreaterThan(0);
        for (var car : allCars) {
            System.out.println(car);
        }
    }

    @Test
    public void testUpdate(){
        var user = repo.findById(9).get();
        user.setName("NissanTest");
        user.setModel("370Test");

        user = repo.save(user);

        Assertions.assertThat(user.getName()).isEqualTo("NissanTest");
        Assertions.assertThat(user.getModel()).isEqualTo("370Test");
    }

}
