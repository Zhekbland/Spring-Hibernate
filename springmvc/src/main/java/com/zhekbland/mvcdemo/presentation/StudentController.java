package com.zhekbland.mvcdemo.presentation;

import com.zhekbland.mvcdemo.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {

    /**
     * Passing model to the showForm for form tags.
     */
    @RequestMapping("/showForm")
    public String showForm(Model model) {

        Student student = new Student();

        model.addAttribute("student", student);

        return "student-form";
    }

    /**
     * Get student from filled forms and redirect to page.
     */
    @RequestMapping("/processForm")
    public String processForm(@ModelAttribute("student") Student student) {

        return "student-confirmation";
    }
}
