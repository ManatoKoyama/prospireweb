package com.prospire.prospireweb.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.prospire.prospireweb.service.BudgetFixService;

import com.prospire.prospireweb.model.GetBudget;

import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/common/budgedFIX")
public class BudgetFixController {

  @Autowired
	private BudgetFixService BFservice;
    
    //保存された予算を表示する
    @GetMapping("/display")
    public String getMethodName(Model model) {
    //
		List<GetBudget> BudgetList =  BFservice.getList();
		model.addAttribute("budgets", BudgetList);
		return "/common/budgedFIX/display";
    }
    
}
