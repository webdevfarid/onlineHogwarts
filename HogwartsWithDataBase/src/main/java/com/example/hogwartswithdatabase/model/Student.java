package com.example.hogwartswithdatabase.model;

import jakarta.persistence.*;

@Entity
public class Student
{
    @Id
    @GeneratedValue
    private int id;
    private int age;
    private  String name;
    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty_id;
    @OneToOne
    private Avatar avatar;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName(String s) {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
