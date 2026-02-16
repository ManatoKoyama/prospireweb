package com.prospire.prospireweb.controller;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class BudgetFixController {

  /** 予算FIX関連のサービス */
  private final BudgetFixService budgetFixService;


  @ModelAttribute
  public BudgetFixForm setUpForm() {
    return new BudgetFixForm();
  }

    /** 保存された予算を表示します。 */
    @GetMapping("/budgetfix")
    public String getMethodName(Model model, HttpSession session) {
      List<GetBudgetFix> BudgetList =  budgetFixService.getList();
      model.addAttribute("budgets", BudgetList);
      // departments と terms をテンプレートに渡す（Vue の選択肢に利用）
      model.addAttribute("departments", budgetFixService.getDepartments());
      model.addAttribute("terms", budgetFixService.getTerms());
      model.addAttribute("session", session);
      return "budgetFix";
    }
    
    /** 予算FIX確認画面へ入力内容をセッションに保存します。 */
    @PostMapping("/comfirm")
    public String BudgetNoOrGoAndComment(Model model, BudgetFixForm fixform, HttpSession budgetFixsession) {
      // sessionに保存
      budgetFixsession.setAttribute("FixForm", fixform);
      return "budgetFix";
    }

    /** 確認後、予算FIXを保存します。バリデーションを実行します。 */
    @PostMapping("/complete")
    public String BudgetFixComplete(Model model, @Validated BudgetFixForm fixform, BindingResult bindingResult, HttpSession budgetFixsession) {
      // sessionから取得
      BudgetFixForm sessionForm = (BudgetFixForm) budgetFixsession.getAttribute("FixForm");
      budgetFixService.ValidateBudgetFix(sessionForm, bindingResult);
      
      if (bindingResult.hasErrors()) {
        return "budgetFix";
      }
      // DBに保存
      budgetFixService.saveBudgetFix(sessionForm);
      return "budgetFix";
    }

    
}
