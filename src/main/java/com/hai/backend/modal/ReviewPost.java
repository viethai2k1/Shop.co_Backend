package com.hai.backend.modal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewPost {
    private String review;
    private int rate;
    private boolean status;
    private int productId;
    private int userId;
}
