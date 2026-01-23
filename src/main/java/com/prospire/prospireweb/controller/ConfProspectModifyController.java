package com.prospire.prospireweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class ConfProspectModifyController {

    @GetMapping("/compBudgetRegistration")
    public String compBudgetRegistration(){
        return "comp-budget-registration"; // 予算登録完了画面 を表示
    }

}