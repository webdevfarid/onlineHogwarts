package com.example.hogwartswithdatabase.controller;

import com.example.hogwartswithdatabase.model.Faculty;
import com.example.hogwartswithdatabase.service.FacultyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/faculty")
public class FacultyController
{
    FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping("create")
    public Faculty createFaculty(@RequestBody Faculty faculty)
    {
        return facultyService.createFaculty(faculty);
    }
    @GetMapping("/get")
    public ResponseEntity<Faculty> getFaculty(@RequestBody int id)
    {
        Faculty faculty=facultyService.findFaculty(id);
        if (faculty == null) {
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Faculty> deleteFaculty(@RequestBody int id)
    {
        facultyService.deleteFaculty(id);
        return  ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public  ResponseEntity<Faculty> updateFaculty(@RequestBody Faculty faculty)
    {
        Faculty faculty1=facultyService.updateFaculty(faculty);
        if(faculty1==null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }
    @GetMapping("/getAll")
    public Collection<Faculty> getAllFaculties()
    {
        return facultyService.getAllFaculties();
    }
    @GetMapping("/getAllByNameOrColor")
    public Collection<Faculty> findAllByNameOrColor(@RequestParam(required = false) String name,
                                                    @RequestParam(required = false) String color)
    {
        return facultyService.findAllByNameOrColor(name, color);
    }

}
