package com.demo.crud.example.repository;

import com.demo.crud.example.entity.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Integer> {
    List<Developer> findByTeamId(Integer id);
}
