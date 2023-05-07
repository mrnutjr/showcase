package com.example.showcase.repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class PersonCustomRepository   {
    @PersistenceContext
    private EntityManager em;



}
