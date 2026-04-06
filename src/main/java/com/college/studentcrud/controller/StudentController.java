package com.college.studentcrud.controller;

import com.college.studentcrud.model.Student;
import com.college.studentcrud.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional; 

@RestController // tell spring that this class is a controller class and it will handle the HTTP requests 
// controller and responce body : controller will handle the HTTP requests and return the response body in JSON format by default
@RequestMapping("/api/students") // base URL for all the endpoints in this controller
public class StudentController {

    private final StudentService studentService;

    @Autowired // tell spring to inject the dependency of studentService
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Get ALL Student

    @GetMapping 
    public ResponseEntity<List<Student>> getAllStudents(){

        List<Student> students = studentService.getAllStudents();

        return ResponseEntity.ok(students);
        // ResponseEntity is a class that represents the HTTP response including the status code, headers and body
        // ok method will return the response with status code 200 and the body will be the list of students
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        Optional<Student> student = studentService.getStudentById(id);

        if(student.isPresent()) {
            return ResponseEntity.ok(student.get());
        } 

        return ResponseEntity.notFound().build();
        
        // build method will return the response with status code 404 and no body
    

    }   

    @GetMapping("/search")
    public ResponseEntity<List<Student>> getStudentsByCourse(@RequestParam String course){

        List<Student> students = studentService.getStudentByCourse(course);
        return ResponseEntity.ok(students);

    }

    @GetMapping("/searchByName")
    public ResponseEntity<List<Student>> searchByName(@RequestParam String name){

        List<Student> students = studentService.searchByName(name);
        return ResponseEntity.ok(students);

    }
    

    @GetMapping("/count")
    public ResponseEntity<String> getCount(){
        Long count = studentService.getTotalCount();
        return ResponseEntity.ok("Total Student in Database: " + count);
    }
}
