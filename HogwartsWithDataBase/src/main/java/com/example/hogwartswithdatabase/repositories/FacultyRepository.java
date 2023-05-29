package com.example.hogwartswithdatabase.repositories;

import com.example.hogwartswithdatabase.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty,Integer>
{
    public List<Faculty> findFacultyByName(String name);
    public Collection<Faculty> findAllByNameOrColorIgnoreCase(String name, String  color);
}
