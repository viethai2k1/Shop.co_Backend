package com.hai.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(columnDefinition = "TEXT")
    private String description;
    private double discount;
    private Date createDate;
    private String name;
    private double price;
    private double rate;
    private int quantity;
    private  int sold;
    private boolean status;
    private boolean isDelete;
    private int totalRate;
    private int test;

    @ManyToMany
    private List<CategoryEntity> categories;

    @ManyToMany
    private List<ImageEntity> images;
}
