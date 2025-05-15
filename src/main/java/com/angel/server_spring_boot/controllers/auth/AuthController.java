package com.angel.server_spring_boot.controllers.auth;

import com.angel.server_spring_boot.domain.User;
import com.angel.server_spring_boot.service.users.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.angel.server_spring_boot.dto.auth.LoginRequestDTO;
import com.angel.server_spring_boot.dto.auth.LoginResponseDTO;
import com.angel.server_spring_boot.dto.global.ErrorResponseDTO;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) { // Inyección de dependencias por constructor
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        String token = userService.login(loginRequestDTO);
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

}
