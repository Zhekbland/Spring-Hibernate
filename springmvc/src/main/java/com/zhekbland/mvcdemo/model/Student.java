package com.zhekbland.mvcdemo.model;

import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;

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

    /**
     * Value for select OPTIONS in JSP.
     */
    private LinkedHashMap<String, String> countryOptions;

    public Student() {
        countryOptions = new LinkedHashMap<>();

        countryOptions.put("BR", "Brasil");
        countryOptions.put("FR", "France");
        countryOptions.put("RU", "Russia");
        countryOptions.put("US", "USA");
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

    public LinkedHashMap<String, String> getCountryOptions() {
        return countryOptions;
    }
}
