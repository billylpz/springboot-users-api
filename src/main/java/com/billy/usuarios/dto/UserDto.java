package com.billy.usuarios.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {
    private Long id;

    @NotBlank
    @Size(min = 2, max = 50)
    private String name;

    @NotBlank
    @Size(min = 2, max = 50)
    private String lastname;

    @Min(0)
    @Max(120)
    @NotNull
    private Integer age;

    @NotBlank
    @Size(min = 9, max = 20)
    private String cellphone;

    @NotBlank
    @Email
    private String email;

    private String urlProfilePhoto;
}
