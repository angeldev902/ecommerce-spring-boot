package com.angel.server_spring_boot.dto.users;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

import com.angel.server_spring_boot.domain.enums.Role;

public class UserDTO {
    @NotBlank(message = "firstName is required")
    @Size(min = 3, message = "firstName must be at least 3 characters long") 
    private String firstName;

    @NotBlank(message = "lastName is required")
    @Size(min = 3, message = "lastName must be at least 3 characters long") 
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(
        regexp = "^\\d{10}$",
        message = "Phone number must be exactly 10 digits"
    )
    private String phone;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
    @Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&/#()_=+{}\\[\\]-])[A-Za-z\\d@$!%*?&/#()_=+{}\\[\\]-]{8,}$",
        message = "Password must contain at least one uppercase letter, one lowercase letter, one number, one special character, and be at least 8 characters long"
    )
    private String password;

    private Role role;

    // Getters y setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                ", firstName:'" + firstName + '\'' +
                ", lastName:'" + lastName + '\'' +
                ", email:'" + email + '\'' +
                ", phone:'" + phone + '\'' +
                ", password:'" + password + '\'' +
                ", role:'" + role + '\'' +
                '}';
    };

}