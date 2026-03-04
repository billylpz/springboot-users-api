package com.billy.usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.billy.usuarios.model.User;

public interface UserRepository extends JpaRepository<User,Long> {
    
    
}
