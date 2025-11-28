package ua.edu.dmitro.lab01.controller;

import org.springframework.web.bind.annotation.*;
import ua.edu.dmitro.lab01.model.Student;
import ua.edu.dmitro.lab01.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping
    public List<Student> getAll() {
        return service.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable int id) {
        return service.getStudentById(id);
    }

    @PostMapping
    public String create(@RequestBody Student student) {
        service.createStudent(student);
        return "Student added successfully!";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable int id, @RequestBody Student student) {
        student.setId(id);
        service.updateStudent(student);
        return "Student updated successfully!";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        service.deleteStudent(id);
        return "Student deleted successfully!";
    }
}

