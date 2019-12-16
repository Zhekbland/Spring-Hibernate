package com.zhekbland.mvcdemo.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CourseCodeConstraintValidator implements ConstraintValidator<CourseCode, String> {

   /**
    * This is a
    */
   private String coursePrefix;

   @Override
   public void initialize(CourseCode courseCode) {
      this.coursePrefix = courseCode.value();
   }

   /**
    * Validation logic. Test if the form data starts with our course prefix
    *
    * @param theCode HTML Form Data Entered by the user
    * @param constraintValidatorContext you can place additional error messages here
    * @return theCode starts with coursePrefix
    */
   @Override
   public boolean isValid(String theCode, ConstraintValidatorContext constraintValidatorContext) {
      return (theCode == null) || theCode.startsWith(this.coursePrefix);
   }
}
