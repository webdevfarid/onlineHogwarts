package com.example.hogwartswithdatabase.repositories;

import com.example.hogwartswithdatabase.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;


public interface StudentRepository extends JpaRepository<Student,Integer>
{
    public List<Student> findStudentByName(String name);
    public Collection<Student> findByAgeBetween(int min,int max);
    public  Student getById(int id);
    public Collection<Student> findById();

}
