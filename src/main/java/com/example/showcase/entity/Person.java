package com.example.showcase.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public
class Person {
    @Id
    private Long id;
    private String firstname;
    private String lastname;
    private String status;
}
