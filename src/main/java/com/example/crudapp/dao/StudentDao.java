package com.example.crudapp.dao;

import com.example.crudapp.entity.Student;

import java.util.List;

public interface StudentDao {
    Student findById(Integer id);

    List<Student> findAll();

    void save(Student student);

    Student update(Student student);

    boolean delete(Integer id);
}
