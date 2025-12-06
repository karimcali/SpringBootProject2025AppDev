package com.example.space.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StaticFallbackController {

    @GetMapping("/.")
    public String redirectDot() {
        return "redirect:/swagger-ui.html";
    }

    @GetMapping("/")
    public String redirectRoot() {
        return "redirect:/swagger-ui.html";
    }
}
