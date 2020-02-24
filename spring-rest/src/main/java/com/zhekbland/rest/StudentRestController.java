package com.zhekbland.rest;

import com.zhekbland.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    @GetMapping("/students")
    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("John", "Carter"));
        students.add(new Student("Sam", "Hobbit"));
        students.add(new Student("Sandy", "Squirrel"));
        students.add(new Student("Ron", "Smith"));
        return students;
    }

    @GetMapping("/mapstudents")
    public Map<Integer, Student> getMapStudents() {
        Map<Integer, Student> students = new TreeMap<>();
        students.put(1, new Student("John", "Carter"));
        students.put(2, new Student("Sam", "Hobbit"));
        students.put(3, new Student("Sandy", "Squirrel"));
        return students;
    }
}
