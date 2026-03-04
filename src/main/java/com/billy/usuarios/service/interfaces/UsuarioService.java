package com.billy.usuarios.service.interfaces;

import java.util.List;
import java.util.Optional;

import com.billy.usuarios.model.User;

public interface UsuarioService {
    List<User> findAll();
    Optional<User> findById(Long id);
    User save(User user);
    User update(User user, Long id);
    void delete(Long id);

}
