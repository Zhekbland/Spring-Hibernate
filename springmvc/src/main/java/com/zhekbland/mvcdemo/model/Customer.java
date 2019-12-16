package com.zhekbland.mvcdemo.model;

import javax.validation.constraints.*;

public class Customer {

    private String firstName;

    /**
     * Adding validation for require fields
     * And catching errors
     */
    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String lastName;

    /**
     * Adding validation for require fields and
     * define the boundaries of the value
     * And catching errors
     *
     * Using Integer instead int for "is required"
     * message to avoid convert error.
     */
    @NotNull(message = "is required")
    @Min(value = 0, message = "must be greater than or equal to zero")
    @Max(value = 10, message = "must be less than or equal to 10")
    private Integer freePasses;

    /**
     * Adding validation for require fields and
     * regexp for the value
     * And catching errors
     */
    @Pattern(regexp = "^[a-zA-Z0-9]{5}", message = "only 5 chars/digits")
    private String postalCode;

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

    public Integer getFreePasses() {
        return freePasses;
    }

    public void setFreePasses(Integer freePasses) {
        this.freePasses = freePasses;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
