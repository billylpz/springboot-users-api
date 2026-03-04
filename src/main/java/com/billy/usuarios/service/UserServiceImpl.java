package com.billy.usuarios.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.billy.usuarios.dto.UserDto;
import com.billy.usuarios.mapper.UserMapper;
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
                .map(u->UserMapper.userToDto(u))
                .collect(Collectors.toList());
    }

    

    @Override
    @Transactional(readOnly = true)
    public Optional<UserDto> findById(Long id) {
        return this.repository.findById(id).map(u->UserMapper.userToDto(u));
    }

    @Override
    @Transactional
    public UserDto save(UserDto userDto) {
        User user = UserMapper.dtoToUser(userDto);

        return UserMapper.userToDto(this.repository.save(user));
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
            return UserMapper.userToDto(this.repository.save(userDb));
        }

        return null;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        this.repository.deleteById(id);
    }

}
