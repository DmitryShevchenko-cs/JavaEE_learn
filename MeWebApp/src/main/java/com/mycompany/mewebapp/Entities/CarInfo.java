package com.mycompany.mewebapp.Entities;

import jakarta.persistence.*;

@Entity
@Table
public class CarInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
}
