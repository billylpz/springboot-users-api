package com.billy.usuarios.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.billy.usuarios.dto.UserDto;
import com.billy.usuarios.mapper.UserMapper;
import com.billy.usuarios.model.User;
import com.billy.usuarios.repository.UserRepository;
import com.billy.usuarios.service.cloudinary.CloudinaryService;
import com.billy.usuarios.service.interfaces.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final CloudinaryService cloudinaryService;

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> findAll() {
        return this.repository.findAll()
                .stream()
                .map(u -> UserMapper.userToDto(u))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserDto> findById(Long id) {
        return this.repository.findById(id).map(u -> UserMapper.userToDto(u));
    }

    @Override
    @Transactional
    public UserDto save(UserDto userDto, MultipartFile file) throws IOException {
        User user = UserMapper.dtoToUser(userDto);
        this.checkAndAssignPhoto(user,file);

        return UserMapper.userToDto(this.repository.save(user));
    }

    public void checkAndAssignPhoto(User user, MultipartFile file) throws IOException {
        // Subir imagen si viene
        if (file != null && !file.isEmpty()) {
            String url;
            url = cloudinaryService.uploadFile(file);
            user.setUrlProfilePhoto(url); // agregas la url al user
        }
    }

    @Override
    @Transactional
    public UserDto update(UserDto userDto, Long id, MultipartFile file) throws IOException {
        Optional<User> userOpt = this.repository.findById(id);

        if (userOpt.isPresent()) {
            User userDb = userOpt.orElseThrow();
            userDb.setName(userDto.getName());
            userDb.setLastname(userDto.getLastname());
            userDb.setEmail(userDto.getEmail());
            userDb.setAge(userDto.getAge());
            userDb.setCellphone(userDto.getCellphone());

            if(userDto.getUrlProfilePhoto()!=null){
                userDb.setUrlProfilePhoto(userDto.getUrlProfilePhoto());
            }

            this.checkAndAssignPhoto(userDb,file) ;

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
