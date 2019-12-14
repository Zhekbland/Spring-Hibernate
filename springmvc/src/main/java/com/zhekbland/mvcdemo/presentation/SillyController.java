package com.zhekbland.mvcdemo.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SillyController {

    @RequestMapping("/showForm")
    public String displayController() {
        return "silly";
    }
}
