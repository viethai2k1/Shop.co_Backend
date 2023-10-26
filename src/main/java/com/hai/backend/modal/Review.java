package com.hai.backend.modal;

import com.hai.backend.entity.ProductEntity;
import com.hai.backend.entity.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    private int id;
    private String review;
    private double rate;
    private boolean status;
    private boolean isDelete;
    private Date createDate;
    private ProductEntity product;
    UserReview user;
}
