package com.billy.usuarios.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.billy.usuarios.dto.UserDto;
import com.billy.usuarios.service.interfaces.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

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
        Optional<UserDto> userOptional = this.userService.findById(id);

        return userOptional.map(u -> ResponseEntity.ok(u))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<?> post(
            @ModelAttribute @Valid UserDto userDto, // ModelAtribute mapea las propiedades del form data a objeto
                                                    // UserDto
            BindingResult rs,
            @RequestParam(required = false) MultipartFile file) {

        if (rs.hasErrors()) {
            return this.validate(userDto, rs);
        }

        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(this.userService.save(userDto, file));

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al subir la imagen");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@ModelAttribute @Valid UserDto userDto, BindingResult rs, @PathVariable Long id,
            @RequestParam(required = false) MultipartFile file) {
        if (rs.hasErrors()) {
            return this.validate(userDto, rs);
        }

        try {
            UserDto dto = this.userService.update(userDto, id, file);

            if (dto != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(dto);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario a modificar no existe!");
            
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al subir la imagen");
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        this.userService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    // validation method
    public ResponseEntity<?> validate(UserDto userDto, BindingResult rs) {
        Map<String, String> errors = new HashMap<>();
        rs.getFieldErrors().forEach(e -> errors.put(e.getField(), e.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

}
