package com.example.hogwartswithdatabase;

import com.example.hogwartswithdatabase.controller.StudentController;
import com.example.hogwartswithdatabase.model.Student;
import com.example.hogwartswithdatabase.repositories.StudentRepository;
import com.example.hogwartswithdatabase.service.StudentService;
import netscape.javascript.JSObject;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public  class HogwartsWithDataBaseApplicationTests
{
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    StudentRepository studentRepository;
    @SpyBean
    StudentService studentService;
    @InjectMocks
    StudentController studentController;


   @Test
    public void createStudentTest() throws Exception
   {
       final String name="student ";
       final int age=21;
       final int id=1;

       JSONObject studentObject=new JSONObject();
       studentObject.put("name",name);
       studentObject.put("age",age);

       Student student=new Student();
       student.setId(id);
       student.getName(name);
       student.setAge(age);

       when(studentRepository.save(any(Student.class))).thenReturn(student);
       when(studentRepository.findById(any(Integer.class))).thenReturn(Optional.of(student));


       mockMvc.perform(MockMvcRequestBuilders
               .post("/createStudent")
               .content(studentObject.toString())
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").value(id))
               .andExpect(jsonPath("$.name").value(name))
               .andExpect(jsonPath("$.age").value(age));
       
   }

}
