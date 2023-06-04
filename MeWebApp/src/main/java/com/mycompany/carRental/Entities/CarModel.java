package com.mycompany.carRental.Entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "car_model")
public class CarModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 8, unique = true)
    private String Number;

    @Column(nullable = false, length = 20)
    private String Color;

    @Column(nullable = false)
    private String Type;

    @Column(nullable = false)
    private int Places;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @OneToMany(mappedBy = "carModel")
    private List<Rental> rentals;

    @Override
    public String toString() {
        return "CarInfo{" +
                "id=" + id +
                ", Number=" + Number +
                ", Color='" + Color + '\'' +
                ", Type='" + Type + '\'' +
                ", Places=" + Places +
                ", car=" + car.toString() +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public int getPlaces() {
        return Places;
    }

    public void setPlaces(int places) {
        Places = places;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }
}
