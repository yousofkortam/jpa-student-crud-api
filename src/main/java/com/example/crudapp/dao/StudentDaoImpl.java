package com.example.crudapp.dao;

import com.example.crudapp.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
public class StudentDaoImpl implements StudentDao {

    EntityManager entityManager;

    @Autowired
    public StudentDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student", Student.class);
        return theQuery.getResultList();
    }

    @Override
    @Transactional
    public void save(Student student) {
        try {
            entityManager.persist(student);
        }catch (Exception e) {
            throw new RuntimeException("Failed to create student with ID: " + student.getId(), e);
        }
    }

    @Override
    @Transactional
    public Student update(Student student) {
        try {
            Integer id = student.getId();
            Student currentStudent = entityManager.find(Student.class, id);

            if (currentStudent != null) {
                currentStudent.setFirstName(student.getFirstName());
                currentStudent.setLastName(student.getLastName());
                currentStudent.setEmail(student.getEmail());
                return currentStudent;
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException("Failed to update student with ID: " + student.getId(), e);
        }
    }
    @Override
    @Transactional
    public boolean delete(Integer id) {
        Student student = entityManager.find(Student.class, id);
        if (student != null) {
            entityManager.remove(student);
            return true;
        }
        return false;
    }
}
