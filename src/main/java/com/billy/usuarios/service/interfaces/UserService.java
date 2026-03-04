package com.billy.usuarios.service.interfaces;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.billy.usuarios.dto.UserDto;

public interface UserService {
    List<UserDto> findAll();
    Optional<UserDto> findById(Long id);
    UserDto save(UserDto user, MultipartFile file) throws IOException;
    UserDto update(UserDto user, Long id, MultipartFile file) throws IOException;
    void delete(Long id);

}
