package com.mycompany.carRental;

import com.mycompany.carRental.Entities.CarModel;
import com.mycompany.carRental.Entities.User;
import com.mycompany.carRental.Repositories.CarModelRepository;
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
public class CarModelRepositoryTest {
    @Autowired
    private CarModelRepository repo;
    @Autowired
    private CarRepository carRepo;

    @Test
    public void TestAddNew(){
        CarModel carModel = new CarModel();
        carModel.setCar(carRepo.findById(1).get());
        carModel.setColor("black");
        carModel.setNumber("AX4380EN");
        carModel.setPlaces(2);
        carModel.setType("Coupe");

        CarModel savedCarModel = repo.save(carModel);
        Assertions.assertThat(savedCarModel).isNotNull();
        Assertions.assertThat(savedCarModel.getId()).isGreaterThan(0);
    }

    @Test
    public void TestAddNew_AlreadyUsedEmail_ThrowException(){
        CarModel carModel = new CarModel();
        carModel.setCar(carRepo.findById(1).get());
        carModel.setColor("black");
        carModel.setNumber("AX4280EN");
        carModel.setPlaces(2);
        carModel.setType("Coupe");

        Assertions.assertThatThrownBy(() -> {repo.save(carModel);})
                .isInstanceOf(Exception.class);
    }
    @Test
    public void TestDelUser_UserFound_DeletedUser(){
        var carModel = repo.findById(2).get();
        repo.deleteById(carModel.getId());
        Assertions.assertThat(repo.findById(carModel.getId())).isEmpty();
    }
    @Test
    public void testListAll(){
        Iterable<CarModel> allCarModels = repo.findAll();
        Assertions.assertThat(allCarModels).hasSizeGreaterThan(0);
        for (var carModel : allCarModels) {
            System.out.println(carModel);
        }
    }

    @Test
    public void testUpdate(){
        var carModel = repo.findById(2).get();
        carModel.setColor("red");
        carModel.setPlaces(5);

        carModel = repo.save(carModel);

        Assertions.assertThat(carModel.getColor()).isEqualTo("red");
        //Assertions.assertThat(carModel.getPlaces() == 5);
    }
}
