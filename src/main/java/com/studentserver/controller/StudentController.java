package com.studentserver.controller;

import com.studentserver.model.Student;
import com.studentserver.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable String studentId){
        Student student = studentService.getStudentById(studentId);
        if(student != null) {
            return new ResponseEntity<>(student, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Student>> getAllStudents(){
        List<Student> students = studentService.getAllStudents();
        if(!students.isEmpty()) {
            return new ResponseEntity<>(students, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Student> saveStudent(@Valid @RequestBody Student student){
        Student newStudent = studentService.addStudent(student);
        return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
    }

    @PutMapping("/update/{studentId}")
    public ResponseEntity<Student> updateStudent(@PathVariable String studentId, @Valid @RequestBody Student student){
        Student updatedStudent = studentService.updateStudent(studentId, student);
        if(updatedStudent != null) {
            return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/remove/{studentId}")
    public ResponseEntity<Void> removeStudent(@PathVariable  String studentId){
        try {
            studentService.deleteStudent(studentId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
