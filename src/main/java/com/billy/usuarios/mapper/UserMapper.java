package com.billy.usuarios.mapper;

import com.billy.usuarios.dto.UserDto;
import com.billy.usuarios.model.User;

public abstract class UserMapper {

     public static UserDto userToDto(User user) {
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

    public static User dtoToUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setLastname(userDto.getLastname());
        user.setEmail(userDto.getEmail());
        user.setCellphone(userDto.getCellphone());
        user.setAge(userDto.getAge());
        user.setUrlProfilePhoto(userDto.getUrlProfilePhoto());
        return user;
    }

}
