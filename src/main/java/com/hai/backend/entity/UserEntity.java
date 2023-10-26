package com.hai.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Primary;

import java.util.Date;
import java.util.List;

@Entity
@Table(name= "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date createDate;
    private String fullname;
    private String username;
    private String email;
    private String password;
    private String phonenumber;
    private String image;
    private boolean status;
    private boolean isDelete;

    @ManyToMany
    private List<RoleEntity> roles;
}
