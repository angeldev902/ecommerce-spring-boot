package com.angel.server_spring_boot.dto.users;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

import com.angel.server_spring_boot.domain.enums.Role;

public class UserUpdateDTO {
    @Size(min = 3, message = "firstName must be at least 3 characters long") 
    private String firstName;

    @Size(min = 3, message = "lastName must be at least 3 characters long") 
    private String lastName;

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
                ", role:'" + role + '\'' +
                '}';
    };

}