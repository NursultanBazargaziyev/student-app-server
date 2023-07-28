package com.studentserver.service;

import com.studentserver.exception.ResourceNotFoundException;
import com.studentserver.model.Student;
import com.studentserver.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student){
        student.setStudentId(UUID.randomUUID().toString().split("-")[0]);
        return studentRepository.save(student);
    }

    public Student getStudentById(String studentId){
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student with ID " + studentId + " not found"));
    }

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public Student updateStudent(String studentId, Student student){
        if(studentRepository.existsById(studentId)) {
            student.setStudentId(studentId);
            return studentRepository.save(student);
        }
        else
            return null;
    }

    public void deleteStudent(String studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Student Not Found");
        }
        studentRepository.deleteById(studentId);
    }


}
