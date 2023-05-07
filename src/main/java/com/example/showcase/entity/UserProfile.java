package com.example.showcase.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public
class UserProfile {
    @Id
    private Long id;
    private String username;
    private String password;
    private String status;
    private String role;
}
