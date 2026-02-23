package com.jeev.kedSpringbootApp.repository;

import com.jeev.kedSpringbootApp.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KedSpringbootAppRepository extends MongoRepository<Student, String> {
    Student findByStudentId(String studentId);
}
