package com.zhekbland.rest;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class SpringRestController {

    @GetMapping("/hello")
    @ResponseBody()
    public String sayHello() {
        return "Hello world!";
    }
}
