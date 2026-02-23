package com.jeev.kedSpringbootApp.controller;

import com.jeev.kedSpringbootApp.model.Student;
import com.jeev.kedSpringbootApp.service.KedSpringbootAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/kedSpringbootApp")
public class KedSpringbootAppController {
    @Autowired
    KedSpringbootAppService kedSpringbootAppService;

    @RequestMapping(value = "/{studentId}", method = RequestMethod.GET)
    public ResponseEntity<?> getStudent(@PathVariable String studentId){
        Student response = kedSpringbootAppService.getStudent(studentId);
        return new ResponseEntity<Student>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> getStudentss(){
        List<Student> response = kedSpringbootAppService.getStudents();
        return new ResponseEntity<List<Student>>(response, HttpStatus.OK);
    }


}
