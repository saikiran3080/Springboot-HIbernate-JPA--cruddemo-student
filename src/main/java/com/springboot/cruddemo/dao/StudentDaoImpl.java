package com.springboot.cruddemo.dao;

import com.springboot.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/* 1. Specialised annotation for repositories.
*  2. Supports Component Scanning
*  3.Translates JDBC Exceptions */
@Repository
public class StudentDaoImpl implements StudentDao{
//    define entity manager
    private EntityManager entityManager;

//    inject entity manager using constructor injection
    @Autowired
    public StudentDaoImpl(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

//    @Transational since we are performing an update -- of spring framework
    @Transactional
    @Override
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }

    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class ,id);
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> theQuery = entityManager.createQuery("from " +
                "Student" , Student.class);

//        return query result
        return theQuery.getResultList();
    }

    @Override
    public List<Student> findByLastName(String lastName) {
        TypedQuery<Student> theQuery = entityManager.createQuery("from Student where " +
                "lastName=:theData" , Student.class);
        theQuery.setParameter("theData" , lastName);
        return theQuery.getResultList();
    }

    @Override
    @Transactional
    public void updateStudent(Student student) {
        entityManager.merge(student);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Student st = entityManager.find(Student.class, id);
        entityManager.remove(st);
    }

    @Override
    public int deleteAll() {
        int numRowsDeleted = entityManager.createQuery("DELETE FROM Student").executeUpdate();

        return numRowsDeleted;
    }


}
