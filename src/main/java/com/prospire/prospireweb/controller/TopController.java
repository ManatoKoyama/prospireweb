package com.prospire.prospireweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class TopController {

    @GetMapping("/top")
    public String top(){
        return "top"; // top.html を表示
    }

    @GetMapping("/achievements")
    public String achievements(){
        return "achievements"; // achievements.html を表示
    }
}