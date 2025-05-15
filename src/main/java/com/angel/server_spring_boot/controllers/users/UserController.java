package com.angel.server_spring_boot.controllers.users;

import com.angel.server_spring_boot.domain.User;
import com.angel.server_spring_boot.service.users.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;
import java.util.List;

import com.angel.server_spring_boot.dto.users.UserDTO;
import com.angel.server_spring_boot.dto.users.UserUpdateDTO;
import com.angel.server_spring_boot.dto.global.SuccessfulDTO;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO userDTO, BindingResult result) {
        if (result.hasErrors()) return ResponseEntity.badRequest().body(result.getAllErrors());
        User user = userService.save(userDTO);
        return ResponseEntity.ok(new SuccessfulDTO("Created user"));
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @Valid @RequestBody UserUpdateDTO userUpdateDTO, BindingResult result) {
        if (result.hasErrors()) return ResponseEntity.badRequest().body(result.getAllErrors());
        User user = userService.update(id, userUpdateDTO);
        return ResponseEntity.ok(new SuccessfulDTO("Updated user"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok(new SuccessfulDTO("Deleted user"));
    }
}
