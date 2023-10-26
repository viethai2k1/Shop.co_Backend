package com.hai.backend.modal;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserReview{
    private int id;
    private Date createDate;
    private String fullname;
    private String image;
    private boolean status;

}
