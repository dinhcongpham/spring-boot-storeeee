package com.jokerP.store.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
