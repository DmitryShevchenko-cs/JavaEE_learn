package com.mycompany.carRental.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "rental")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_model_id", nullable = false)
    private CarModel carModel;

    @Column(name = "date_of_issue", nullable = false)
    private LocalDate dateOfIssue;

    @Column(name = "return_date", nullable = false)
    private LocalDate returnDate;

    @Override
    public String toString() {
        return "Rental{" +
                "id=" + id +
                ", user=" + user.toString() +
                ", carInfo=" + carModel.toString() +
                ", dateOfIssue=" + dateOfIssue +
                ", returnDate=" + returnDate +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CarModel getCar() {
        return carModel;
    }

    public void setCar(CarModel car) {
        this.carModel = car;
    }

    public LocalDate getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(LocalDate dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
