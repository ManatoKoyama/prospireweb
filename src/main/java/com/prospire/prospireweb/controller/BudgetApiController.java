package com.prospire.prospireweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prospire.prospireweb.model.GetBudgetFix;
import com.prospire.prospireweb.service.BudgetFixService;

@RestController
@RequestMapping("/api")
public class BudgetApiController {
  @Autowired BudgetFixService service;
  
  @GetMapping("/budgets")
  public List<GetBudgetFix> getAllBudgets(@org.springframework.web.bind.annotation.RequestParam(name = "department", required = false) String department) {
    if (department == null || department.isBlank()) {
      return service.getList();
    }
    return service.findByDepartment(department);
  }

  @GetMapping("/departments")
  public List<String> getDepartments() {
    return service.getDepartments();
  }

  @GetMapping("/terms")
  public List<String> getTerms() {
    return service.getTerms();
  }

  @GetMapping("/budgets/total")
  public long getTotal(@org.springframework.web.bind.annotation.RequestParam(name = "department", required = false) String department) {
    return service.getTotalByDepartment(department);
  }
}
