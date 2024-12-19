package com.springboot.cruddemo.dao;

import com.springboot.cruddemo.entity.Student;

import java.util.List;

public interface StudentDao {
    void save(Student theStudent);
    Student findById(Integer id);
    List<Student> findAll();
    List<Student> findByLastName(String lastName);
    void updateStudent(Student student);
    void delete(Integer id);
    int deleteAll();

}
