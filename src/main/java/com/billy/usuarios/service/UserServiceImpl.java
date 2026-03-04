package com.billy.usuarios.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.billy.usuarios.model.User;
import com.billy.usuarios.repository.UserRepository;
import com.billy.usuarios.service.interfaces.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return this.repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
       return this.repository.findById(id);
    }

    @Override
    @Transactional
    public User save(User user) {
        return this.repository.save(user);
    }

    @Override
    @Transactional
    public User update(User user, Long id) {
        Optional<User> userOpt= this.findById(id);

        if(userOpt.isPresent()){
            User userDb= userOpt.orElseThrow();
            userDb.setName(user.getName());
            userDb.setLastname(user.getLastname());
            userDb.setEmail(user.getEmail());
            userDb.setCellphone(user.getCellphone());
            userDb.setUrlProfilePhoto(user.getUrlProfilePhoto());
            return this.repository.save(userDb);
        }

        return null;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        this.repository.deleteById(id);
    }

}
