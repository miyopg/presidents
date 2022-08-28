package com.example.presidents.repository;


import com.example.presidents.model.entity.President;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PresidentsRepository extends JpaRepository<President, Long> {



}
