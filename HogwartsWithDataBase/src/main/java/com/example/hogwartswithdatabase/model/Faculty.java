package com.example.hogwartswithdatabase.model;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.Collection;


@Entity
public class Faculty
{
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String color;
    @OneToMany(mappedBy ="faculty_id")
    Collection<Student> students;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
