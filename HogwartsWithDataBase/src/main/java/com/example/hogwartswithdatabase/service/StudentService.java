package com.example.hogwartswithdatabase.service;

import com.example.hogwartswithdatabase.model.Student;
import com.example.hogwartswithdatabase.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;


@Service
public class StudentService
{
    @Autowired
    final StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository)
    {
        this.studentRepository = studentRepository;
    }
    public Student createStudent(Student student)
    {
        return studentRepository.save(student);
    }
    public  Student findStudent(int id)
    {
        return  studentRepository.findById(id).get();
    }

   public List<Student> findStudentByName(String name)
   {
       return  studentRepository.findStudentByName(name);
   }
    public void deleteStudent(int id)
    {
        studentRepository.deleteById(id);
    }

    public Student updateBook(Student student)
    {
        return  studentRepository.save(student);
    }
    public Collection<Student> getAllStudents()
    {
        return studentRepository.findAll();
    }
    public  Collection<Student> findByAgeBetween(int a,int b)
    {
        return studentRepository.findByAgeBetween(a,b);
    }

}
