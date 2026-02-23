package com.jeev.kedSpringbootApp.service;

import com.jeev.kedSpringbootApp.model.Student;
import com.jeev.kedSpringbootApp.repository.KedSpringbootAppRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class KedSpringbootAppService {

    private final KedSpringbootAppRepository kedSpringbootAppRepository;

    // Spring Boot 4 automatically autowires this constructor
    public KedSpringbootAppService(KedSpringbootAppRepository repository) {
        this.kedSpringbootAppRepository = repository;
    }

    public Student getStudent(String studentId) {
        return kedSpringbootAppRepository.findByStudentId(studentId);
    }

    public List<Student> getStudents() {
        // Safe check for empty collection
        List<Student> all = kedSpringbootAppRepository.findAll();
        return all.isEmpty() ? null : all;
    }
}
