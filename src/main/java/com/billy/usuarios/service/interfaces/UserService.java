package com.billy.usuarios.service.interfaces;

import java.util.List;
import java.util.Optional;

import com.billy.usuarios.dto.UserDto;

public interface UserService {
    List<UserDto> findAll();
    Optional<UserDto> findById(Long id);
    UserDto save(UserDto user);
    UserDto update(UserDto user, Long id);
    void delete(Long id);

}
