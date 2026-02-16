package com.prospire.prospireweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ログイン画面を表示するコントローラ。
 */
@Controller
@RequestMapping("")
public class LoginController {

    @GetMapping("/login")
    public String login(){
        return "login"; // login.html を表示
    }
}