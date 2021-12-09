package com.demo.crud.example.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Developer")
public class Developer {

    @Id
    @GeneratedValue
    private int id;
    @Column(name = "team_id", nullable = false)
    private int teamId;
    private String name;
    @Column(name = "phone_number", nullable = false)
    private String phone_number;

    public Developer(int team_id, String name, String phone_number){
        this.teamId = team_id;
        this.name = name;
        this.phone_number = phone_number;
    }
}
