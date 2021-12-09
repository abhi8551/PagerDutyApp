package com.demo.crud.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Team")
public class Team {
    public Team(String name){
        this.name = name;
    }
    @Id
    @GeneratedValue
    private int id;
    private String name;
}
