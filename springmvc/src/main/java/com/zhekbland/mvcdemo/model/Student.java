package com.zhekbland.mvcdemo.model;

import org.springframework.stereotype.Component;

public class Student {

    /**
     * Value for input in JSP.
     */
    private String firstName;

    /**
     * Value for input in JSP.
     */
    private String lastName;

    /**
     * Value for select in JSP.
     */
    private String country;


    public Student() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
