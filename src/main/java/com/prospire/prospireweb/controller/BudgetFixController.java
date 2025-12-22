package com.prospire.prospireweb.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.prospire.prospireweb.service.BudgetFixService;

import jakarta.servlet.http.HttpSession;

import com.prospire.prospireweb.form.BudgetFixForm;
import com.prospire.prospireweb.model.GetBudgetFix;

import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
//@RequestMapping("/budgetfix")
public class BudgetFixController {

  @Autowired
	public BudgetFixService BFservice;

  private HttpSession budgetFixsession;

  @ModelAttribute
  public BudgetFixForm setUpForm() {
    return new BudgetFixForm();
  }

    //保存された予算を表示する
    @GetMapping("/budgetfix")
    public String getMethodName(Model model) {
      //
		  List<GetBudgetFix> BudgetList =  BFservice.getList();
		  model.addAttribute("budgets", BudgetList);
      model.addAttribute("session", budgetFixsession);
		  return "budgetFix";
    }
    
    //予算FIX確認画面へ入力内容とともにする遷移sessionに保存
    @PostMapping("/comfirm")
    public String BudgetNoOrGoAndComment(Model model, BudgetFixForm fixform, HttpSession budgetFixsession) {
      //sessionに保存
      budgetFixsession.setAttribute("FixForm", fixform);
      return "budgetFix";
    }

    @PostMapping("/complete")
    public String BudgetFixComplete(Model model, @Validated BudgetFixForm fixform, BindingResult bindingResult, HttpSession budgetFixsession) {
      //sessionから取得
      BudgetFixForm sessionForm = (BudgetFixForm) budgetFixsession.getAttribute("FixForm");
      BFservice.ValidateBudgetFix(sessionForm, bindingResult);
      
      if (bindingResult.hasErrors()) {
        return "budgetFix";
      }
      //DBに保存
      BFservice.saveBudgetFix(sessionForm);
      return "budgetFix";
    }

    
}
