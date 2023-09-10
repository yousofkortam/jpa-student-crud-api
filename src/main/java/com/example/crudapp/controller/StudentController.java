package com.example.crudapp.controller;

import com.example.crudapp.dao.StudentDao;
import com.example.crudapp.entity.Student;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    StudentDao studentDao;

    @Autowired
    public StudentController(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @PostMapping("/create")
    public Student save(@RequestBody Student student) {
        studentDao.save(student);
        return student;
    }

    @GetMapping("/all")
    public List<Student> getAll() {
        return studentDao.findAll();
    }

    @GetMapping()
    public Student getStudentById(@RequestParam Integer id) {
        return studentDao.findById(id);
    }

    @PostMapping("/update")
    public Student updateStudent(@RequestBody Student newStudent) {
        return studentDao.update(newStudent);
    }

    @DeleteMapping("/delete")
    public boolean deleteStudent(@RequestParam Integer id) {
        return studentDao.delete(id);
    }


}
