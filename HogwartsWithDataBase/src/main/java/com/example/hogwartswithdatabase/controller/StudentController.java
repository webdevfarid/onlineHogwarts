package com.example.hogwartswithdatabase.controller;

import com.example.hogwartswithdatabase.model.Student;
import com.example.hogwartswithdatabase.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/students")
public class StudentController
{
    StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/create")
    public Student createstudent(@RequestBody Student student)
    {
        return  studentService.createStudent(student);
    }
    @GetMapping("/get")
    public ResponseEntity<Student> getStudent(@RequestBody int id)
    {
        Student student=studentService.findStudent(id);
        if (student == null) {
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Student> deleteStudent(@RequestBody int id)
    {
        studentService.deleteStudent(id);
        return  ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public  ResponseEntity<Student> updateStudent(@RequestBody Student student)
    {
        Student student1=studentService.updateBook(student);
        if(student1==null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }
    @GetMapping("/getAll")
    public Collection<Student> getAllStudents()
    {
        return studentService.getAllStudents();
    }
    @GetMapping("/getBetwenAges")
    public Collection<Student> findByAgeBetween(@RequestParam int max,
                                                @RequestParam int min)
    {
       return studentService.findByAgeBetween(max, min);

    }

}
