package com.example.hairnada.controller.main;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/*")
@RequiredArgsConstructor
public class MainController {
    @GetMapping("/main")
    public String main(){
        return "index";
    }
}