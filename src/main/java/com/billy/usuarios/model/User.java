package com.billy.usuarios.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "usuarios")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 50)
    @Column(length = 50)
    private String name;

    @NotBlank
    @Size(min = 2, max = 50)
    @Column(length = 50)
    private String lastname;

    @Min(0)
    @Max(120)
    private Integer age;

    @NotBlank
    @Size(min = 9, max = 20)
    @Column(length = 20)
    private String cellphone;

    @NotBlank
    @Email
    @Column(unique = true)
    private String email;

    @Column(name = "url_profile_photo")
    private String urlProfilePhoto;


}
