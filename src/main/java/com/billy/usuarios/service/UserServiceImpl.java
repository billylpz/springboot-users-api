package com.billy.usuarios.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.catalina.realm.UserDatabaseRealm.UserDatabasePrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.billy.usuarios.dto.UserDto;
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
    public List<UserDto> findAll() {
        return this.repository.findAll()
                .stream()
                .map(u->this.userToDto(u))
                .collect(Collectors.toList());
    }

    

    @Override
    @Transactional(readOnly = true)
    public Optional<UserDto> findById(Long id) {
        return this.repository.findById(id).map(u->this.userToDto(u));
    }

    @Override
    @Transactional
    public UserDto save(UserDto userDto) {
        User user = this.dtoToUser(userDto);

        return this.userToDto(this.repository.save(user));
    }

    public UserDto userToDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setLastname(user.getLastname());
        dto.setEmail(user.getEmail());
        dto.setCellphone(user.getCellphone());
        dto.setAge(user.getAge());
        dto.setUrlProfilePhoto(user.getUrlProfilePhoto());
        return dto;
    }

    public User dtoToUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setLastname(userDto.getLastname());
        user.setEmail(userDto.getEmail());
        user.setCellphone(userDto.getCellphone());
        user.setAge(userDto.getAge());
        user.setUrlProfilePhoto(userDto.getUrlProfilePhoto());
        return user;
    }

    @Override
    @Transactional
    public UserDto update(UserDto userDto, Long id) {
        Optional<User> userOpt = this.repository.findById(id);

        if (userOpt.isPresent()) {
            User userDb = userOpt.orElseThrow();
            userDb.setName(userDto.getName());
            userDb.setLastname(userDto.getLastname());
            userDb.setEmail(userDto.getEmail());
            userDb.setAge(userDto.getAge());
            userDb.setCellphone(userDto.getCellphone());
            userDb.setUrlProfilePhoto(userDto.getUrlProfilePhoto());
            return this.userToDto(this.repository.save(userDb));
        }

        return null;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        this.repository.deleteById(id);
    }

}
