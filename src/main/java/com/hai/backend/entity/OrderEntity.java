package com.hai.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user_order")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date createDate;
    private double totalprice;
    private String lv1;
    private String lv2;
    private String lv3;
    private String lv4;
    private int status;

    @ManyToOne
    UserEntity userEntity;

    @OneToMany
    List<OrderProductEntity> products;
}
