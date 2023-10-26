package com.hai.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MathController {
    @GetMapping("/api/tinhtong")
    public double Sum(@RequestParam(defaultValue = "0") double a, @RequestParam(defaultValue = "0") double b) {
        return a + b;
    }

}
