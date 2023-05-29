package com.example.hogwartswithdatabase.service;

import com.example.hogwartswithdatabase.model.Faculty;

import com.example.hogwartswithdatabase.repositories.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class FacultyService
{
    @Autowired
    final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty)
    {
        return  facultyRepository.save(faculty);
    }
    public  Faculty findFaculty(int id)
    {
        return  facultyRepository.findById(id).get();
    }

    public List<Faculty> findFacultyByName(String name)
    {
        return  facultyRepository.findFacultyByName(name);
    }
    public void deleteFaculty(int id)
    {
        facultyRepository.deleteById(id);
    }

    public Faculty updateFaculty(Faculty faculty)
    {
        return  facultyRepository.save(faculty);
    }
    public Collection<Faculty> getAllFaculties()
    {
        return facultyRepository.findAll();
    }
    public Collection<Faculty> findAllByNameOrColor(String name, String color)
    {
       return facultyRepository.findAllByNameOrColorIgnoreCase(name, color);
    }
}
