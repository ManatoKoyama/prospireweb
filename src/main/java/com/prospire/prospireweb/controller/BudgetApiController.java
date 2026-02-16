package com.prospire.prospireweb.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prospire.prospireweb.model.GetBudgetFix;
import com.prospire.prospireweb.service.BudgetFixService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BudgetApiController {
  private final BudgetFixService budgetFixService;
  
  @GetMapping("/budgets")
  public List<GetBudgetFix> getAllBudgets(@org.springframework.web.bind.annotation.RequestParam(name = "department", required = false) String department) {
    if (department == null || department.isBlank()) {
      return budgetFixService.getList();
    }
    return budgetFixService.findByDepartment(department);
  }

  @GetMapping("/departments")
  public List<String> getDepartments() {
    return budgetFixService.getDepartments();
  }

  @GetMapping("/terms")
  public List<String> getTerms() {
    return budgetFixService.getTerms();
  }

  @GetMapping("/budgets/total")
  public long getTotal(@org.springframework.web.bind.annotation.RequestParam(name = "department", required = false) String department) {
    return budgetFixService.getTotalByDepartment(department);
  }
}
