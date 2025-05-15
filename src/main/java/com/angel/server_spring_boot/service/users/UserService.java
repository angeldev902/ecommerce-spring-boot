package com.angel.server_spring_boot.service.users;

import com.angel.server_spring_boot.security.PasswordService;
import com.angel.server_spring_boot.domain.User;
import com.angel.server_spring_boot.infrastructure.users.UserRepository;
import org.springframework.stereotype.Service;
import com.angel.server_spring_boot.dto.auth.LoginRequestDTO;
import com.angel.server_spring_boot.security.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import com.angel.server_spring_boot.dto.users.UserDTO;
import com.angel.server_spring_boot.dto.users.UserUpdateDTO;
import com.angel.server_spring_boot.utils.StringUtils;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordService passwordService;
    private final JwtService jwtService;

    public UserService(UserRepository userRepository, PasswordService passwordService, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordService = passwordService;
        this.jwtService = jwtService;
    }

    public String login(LoginRequestDTO loginRequestDTO) {
        User user = userRepository.findByEmailAndDeletedFalse(loginRequestDTO.getEmail()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        if(!passwordService.matchesPassword(loginRequestDTO.getPassword(), user.getPassword())) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Incorrect password");
        String token = jwtService.generateToken(user.getEmail());
        return token;
    }

    public List<User> findAll() {
        return userRepository.findAllByDeletedFalse();
    }

    public User save(UserDTO userDTO) {
        boolean exists = userRepository.existsByEmail(userDTO.getEmail());
        if (exists) throw new ResponseStatusException(HttpStatus.CONFLICT, "User with this email already exists");
        boolean validPhone = userRepository.existsByPhone(userDTO.getPhone());
        if (validPhone) throw new ResponseStatusException(HttpStatus.CONFLICT, "User with this phone already exists");
        String hashedPassword = passwordService.hashPassword(userDTO.getPassword());
        userDTO.setPassword(hashedPassword);

        User user = User.fromDTO(userDTO);
        return userRepository.save(user);
    }

    public User update(Long id, UserUpdateDTO userUpdateDTO) {
        User user = userRepository.findByIdAndDeletedFalse(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        if (StringUtils.isNotBlank(userUpdateDTO.getFirstName())) user.setFirstName(userUpdateDTO.getFirstName());
        if (StringUtils.isNotBlank(userUpdateDTO.getLastName())) user.setLastName(userUpdateDTO.getLastName());
        if ((userUpdateDTO.getRole()) != null) user.setRole(userUpdateDTO.getRole());
        return userRepository.save(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    public User deleteById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        user.setDeleted(true);
        return userRepository.save(user);
    }
    
}
