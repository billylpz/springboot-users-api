package com.billy.usuarios.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.billy.usuarios.model.User;
import com.billy.usuarios.service.interfaces.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping()
    public ResponseEntity<?> get() {
        return ResponseEntity.ok(this.userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<User> userOptional = this.userService.findById(id);

        return userOptional.map(u -> ResponseEntity.ok(u))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<?> post(@RequestBody @Valid User user, BindingResult rs) {
        if(rs.hasErrors()){
            return this.validate(user, rs);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.save(user));
    }


     @PutMapping("/{id}")
    public ResponseEntity<?> put(@RequestBody @Valid User user, BindingResult rs, @PathVariable Long id) {
        if(rs.hasErrors()){
            return this.validate(user, rs);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.update(user,id));
    }



    // validation method
    public ResponseEntity<?> validate(User user, BindingResult rs) {
        Map<String, String> errors = new HashMap<>();
        rs.getFieldErrors().forEach(e -> errors.put(e.getField(), e.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

}
