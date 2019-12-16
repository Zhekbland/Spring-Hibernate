package com.zhekbland.mvcdemo.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * @Constraint is helper class that contains business rules
 * validation logic
 */
@Constraint(validatedBy = CourseCodeConstraintValidator.class)
/*
 * @Target apply our annotation to a method or field
 */
@Target({ElementType.METHOD, ElementType.FIELD})
/*
 * @Retention  retain this annotation in the
 * Java class file.
 * Process it at runtime
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface CourseCode {

    // define default course code
    String value() default "LUV";

    // define default error message
    public String message() default "must start with LUV";

    // define default groups
    public Class<?>[] groups() default {};

    // define default payloads
    public Class<? extends Payload>[] payload() default {};
}
