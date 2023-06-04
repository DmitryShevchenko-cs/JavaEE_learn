package com.mycompany.carRental.Entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 45)
    private String name;

    @Column(nullable = false, length = 45, unique = true)
    private String Model;

    @Column(nullable = false, length = 25)
    private String carClass;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private List<CarModel> carModelList;

    @Override
    public String toString() {
        return name + " " + Model + " " + carClass;
    }
    public String getNameAndModel(){
        return name + " " + Model;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }


    public String getCarClass() {
        return carClass;
    }

    public void setCarClass(String carClass) {
        this.carClass = carClass;
    }

    public List<CarModel> getCarInfoList() {
        return carModelList;
    }

    public void setCarInfoList(List<CarModel> carModelList) {
        this.carModelList = carModelList;
    }
}
