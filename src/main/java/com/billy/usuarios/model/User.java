package com.billy.usuarios.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "usuarios")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 50, nullable = false)
    private String lastname;

    @Column(nullable = false)
    private Integer age;

    @Column(length = 20, nullable = false)
    private String cellphone;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "url_profile_photo")
    private String urlProfilePhoto;

}
