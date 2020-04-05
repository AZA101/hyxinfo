package com.mc.hyxinfo.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class WebTest {
    @GetMapping("/list")
    public void list(){
        System.out.println("进入到后端项目中来");
    }
}
