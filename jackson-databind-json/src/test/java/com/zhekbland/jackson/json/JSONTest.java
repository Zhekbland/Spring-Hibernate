package com.zhekbland.jackson.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class JSONTest {

    @Test
    public void whenWeReadJsonAndConvertToStudent() {
        ObjectMapper mapper = new ObjectMapper();
        Student result = null;
        Student expected = new Student(14, "Mario", "Rossi", true);
        try {
            result = mapper.readValue(new File("data/sample-light.json"), Student.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(result, is(expected));
    }

    @Test
    public void whenWeReadJsonAndConvertToEmployee() {
        ObjectMapper mapper = new ObjectMapper();
        Employee result = null;
        Address address = new Address("100 Main St", "Philadelphia", "Pennsylvania", "19103",
                "USA");
        String[] language = {"Java", "C#", "Python", "JavaScript"};
        Employee expected = new Employee(14, "Mario", "Rossi", true,
                address, language);
        try {
            result = mapper.readValue(new File("data/sample-full.json"), Employee.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(result, is(expected));
    }
}