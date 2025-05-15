package com.angel.server_spring_boot.domain;
import jakarta.persistence.*;
import com.angel.server_spring_boot.domain.enums.Role;
import java.time.LocalDateTime;

import com.angel.server_spring_boot.dto.users.UserDTO;

@Entity
@Table(name = "users")
public class User {
    //Table columns
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String phone;

    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.USER;

    @Column(nullable = false)
    private boolean deleted = false; // Soft delete

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    // Constructors
    public User() {} // Constructor required by JPA

    public User(String firstName, String lastName, String email, String phone, String password, Role role) { 
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.role = role != null ? role : Role.USER;
    }

    // static methods
    public static User fromDTO(UserDTO dto) {
    return new User(
            dto.getFirstName(),
            dto.getLastName(),
            dto.getEmail(),
            dto.getPhone(),
            dto.getPassword(),
            dto.getRole()
        );
    }

    @Override
    public String toString() {
        return "User{" +
                "id:" + id +
                ", firstName:'" + firstName + '\'' +
                ", lastName:'" + lastName + '\'' +
                ", email:'" + email + '\'' +
                ", phone:'" + phone + '\'' +
                ", password:'" + password + '\'' +
                ", role:'" + role + '\'' +
                '}';
    };

    // Getters y Setters
    public Long getId() {
        return id;
    }

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

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    //Persist of dates
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
